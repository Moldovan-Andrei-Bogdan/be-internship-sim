package com.mecorp.security.components;

import com.mecorp.model.Authority;
import com.mecorp.model.UserEntity;
import com.mecorp.repository.UserEntityRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultUserDetailsService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    public DefaultUserDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = this.userEntityRepository.findByEmail(s);

        if (userEntity == null) {
            throw new UsernameNotFoundException("Could not find any user with the given email");
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), this.convertToGrantedAuthorities(userEntity.getAuthorities()));
    }

    private List<GrantedAuthority> convertToGrantedAuthorities(Set<Authority> authorities) {
        return authorities.stream()
              .map(authority -> new SimpleGrantedAuthority(authority.getRole().name))
              .collect(Collectors.toList());
    }
}
