package com.example.forum.controller;

import com.example.forum.Form.LoginForm;
import com.example.forum.config.security.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);

            String token = tokenService.gerarToken(authentication);

            System.out.println(token);

            return ResponseEntity.ok().build();
        }catch (AuthenticationException e){

            return ResponseEntity.badRequest().build();
        }
    }
}
