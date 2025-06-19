package com.uncode.stop.rest_api.security;

import com.uncode.stop.rest_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = usuarioService.findByCuenta(username).orElseThrow();
        return UserPrincipal.builder()
                .userId(user.getId().toString())
                .username(user.getCuenta())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRol().toString())))
                .password(user.getClave())
                .build();
    }
}
