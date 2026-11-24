package com.example.academia.security;

import com.example.academia.model.enums.Papel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtService {

    private static final String CLAIM_PAPEL = "papel";

    private final SecretKey chave;
    private final long expiracaoMs;

    public JwtService(@Value("${app.jwt.secret}") String secret,
                      @Value("${app.jwt.expiracao-minutos}") long expiracaoMinutos) {
        this.chave = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiracaoMs = expiracaoMinutos * 60_000;
    }

    public String gerarToken(UsuarioAutenticado usuario) {
        Date agora = new Date();
        return Jwts.builder()
                .subject(String.valueOf(usuario.getId()))
                .claim(CLAIM_PAPEL, usuario.getPapel().name())
                .issuedAt(agora)
                .expiration(new Date(agora.getTime() + expiracaoMs))
                .signWith(chave)
                .compact();
    }

    /* Token inválido, adulterado ou expirado resulta em Optional vazio. */
    public Optional<UsuarioAutenticado> extrairUsuario(String token) {
        try {
            Claims claims = Jwts.parser().verifyWith(chave).build()
                    .parseSignedClaims(token).getPayload();
            Long id = Long.valueOf(claims.getSubject());
            Papel papel = Papel.valueOf(claims.get(CLAIM_PAPEL, String.class));
            return Optional.of(UsuarioAutenticado.deToken(id, papel));
        } catch (JwtException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
