package com.mecorp.security.components;

import com.mecorp.exception.NotFoundException;
import com.mecorp.facade.UserEntityFacade;
import com.mecorp.model.Authority;
import com.mecorp.model.UserEntity;
import com.mecorp.utils.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mecorp.utils.JwtTokenUtil.HEADER_STRING;
import static com.mecorp.utils.JwtTokenUtil.TOKEN_PREFIX;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserEntityFacade userEntityFacade;

    private final JwtTokenUtil jwtTokenUtil;

    public JwtTokenFilter(UserEntityFacade userEntityFacade, JwtTokenUtil jwtTokenUtil) {
        this.userEntityFacade = userEntityFacade;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(HEADER_STRING);
        String email;
        String jwtToken;

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        jwtToken = header.replace(TOKEN_PREFIX, "").trim();

        try {
            email = jwtTokenUtil.getUsernameFromToken(jwtToken);
        } catch (Exception e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getWriter().write(e.getMessage());
            return;
        }

        if (email == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        try {
            UserEntity userEntity = this.userEntityFacade.findByEmail(email);

            if (this.jwtTokenUtil.validateToken(jwtToken, userEntity)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userEntity.getEmail(),
                        userEntity.getPassword(),
                        this.convertToGrantedAuthorities(userEntity.getAuthorities())
                );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        } catch (NotFoundException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getWriter().write("Not Authorized");
        }
    }

    private List<GrantedAuthority> convertToGrantedAuthorities(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().name))
                .collect(Collectors.toList());
    }
}
