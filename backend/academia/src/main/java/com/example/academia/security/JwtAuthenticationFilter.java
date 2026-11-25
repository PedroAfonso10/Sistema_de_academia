package com.example.academia.security;

import com.example.academia.model.entity.Pessoa;
import com.example.academia.repository.IPessoaRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/*
  Lê o token JWT do header Authorization e autentica a pessoa na requisição.
  O token só identifica quem é (id); estado e papel vêm do banco, então
  pessoa inexistente ou inativa não é autenticada e mudança de papel vale na hora.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String PREFIXO = "Bearer ";

    private final JwtService jwtService;
    private final IPessoaRepository ipessoaRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith(PREFIXO)) {
            jwtService.extrairUsuario(header.substring(PREFIXO.length()))
                    .flatMap(usuario -> ipessoaRepository.findById(usuario.getId()))
                    .filter(Pessoa::isAtivo)
                    .map(pessoa -> UsuarioAutenticado.deConta(pessoa, null))
                    .ifPresent(usuario -> {
                        var auth = new UsernamePasswordAuthenticationToken(
                                usuario, null, usuario.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    });
        }
        chain.doFilter(request, response);
    }
}
