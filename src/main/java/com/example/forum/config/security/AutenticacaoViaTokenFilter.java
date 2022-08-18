package com.example.forum.config.security;

import com.example.forum.config.security.service.TokenService;
import com.example.forum.model.Usuario;
import com.example.forum.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);

        boolean valido = tokenService.isTokenValido(token);

        if (valido){
            autenticarUsuario(token);
        }

        filterChain.doFilter(request,response);
    }

    private void autenticarUsuario(String token) {
        UUID idUsuario = tokenService.getIdUsuario(token);
        Optional<Usuario> id = usuarioRepository.findById(idUsuario);
        System.out.println(id);
        UsernamePasswordAuthenticationToken autentication = new UsernamePasswordAuthenticationToken(id.get(), null, id.get().getAuthorities() );
        SecurityContextHolder.getContext().setAuthentication(autentication);
    }

    private String recuperarToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
