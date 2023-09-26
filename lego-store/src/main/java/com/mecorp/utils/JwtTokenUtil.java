package com.mecorp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mecorp.model.Authority;
import com.mecorp.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class JwtTokenUtil implements Serializable {
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "mecorplegostore";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    private final ObjectMapper objectMapper;

    public JwtTokenUtil() {
        this.objectMapper = new ObjectMapper();
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserEntity userEntity) throws JsonProcessingException {
        Claims claims = Jwts.claims().setSubject(userEntity.getEmail());
        Set<Authority> userRoles = userEntity.getAuthorities();
        List<String> roleNames = userRoles.stream().map(authority -> authority.getRole().name).toList();

        String jsonUserRoles = this.objectMapper.writeValueAsString(roleNames);
        claims.put("roles", jsonUserRoles);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("mecorplegostore")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    @SuppressWarnings("unchecked")
    public List<String> getRoles(String token) {
        return (List<String>) this.getAllClaimsFromToken(token).get("roles");
    }

    public Boolean validateToken(String token, UserEntity userEntity) {
        final String email = getUsernameFromToken(token);
        return (
                email.equals(userEntity.getEmail())
                        && !isTokenExpired(token));
    }
}
