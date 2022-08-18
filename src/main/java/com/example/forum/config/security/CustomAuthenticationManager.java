package com.example.forum.config.security;

import com.example.forum.config.security.service.AutenticacaoService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CustomAuthenticationManager implements AuthenticationManager {

    @Inject
    AutenticacaoService autenticacaoService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("gian"));
        return new UsernamePasswordAuthenticationToken(authentication.getName(),
                authentication.getCredentials(),
                grantedAuthorities);

    }
}
