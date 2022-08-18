package com.example.forum.config.security;

import com.example.forum.config.security.service.AutenticacaoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.inject.Inject;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Inject
    AutenticacaoService autenticacaoService;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> {
                try {
                    authz
                            .antMatchers(HttpMethod.GET,"/topicos").permitAll()
                            .antMatchers(HttpMethod.GET,"/topicos/*").permitAll()
                            .anyRequest().authenticated()
                            .and().formLogin();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            )
            .httpBasic(withDefaults())
                .authenticationManager(new CustomAuthenticationManager());

        return http.build();
    }


}
