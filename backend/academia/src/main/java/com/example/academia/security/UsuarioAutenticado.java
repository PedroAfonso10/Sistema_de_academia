package com.example.academia.security;

import com.example.academia.model.entity.Pessoa;
import com.example.academia.model.enums.Papel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/*
  - No login: criado a partir da Pessoa + hash (deConta), para o Spring comparar a senha.
  - Nas requisições: criado a partir do token (deToken), sem senha.
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioAutenticado implements UserDetails {

    private final Long id;
    private final Papel papel;
    private final boolean ativo;
    private final String senhaHash;

    public static UsuarioAutenticado deConta(Pessoa pessoa, String senhaHash) {
        return new UsuarioAutenticado(pessoa.getId(), pessoa.getPapel(), pessoa.isAtivo(), senhaHash);
    }

    public static UsuarioAutenticado deToken(Long id, Papel papel) {
        return new UsuarioAutenticado(id, papel, true, null);
    }

    /* ADMIN possui todas as permissões de FUNCIONARIO. */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (papel == Papel.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_FUNCIONARIO"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_" + papel.name()));
    }

    @Override
    public String getPassword() {
        return senhaHash;
    }

    @Override
    public String getUsername() {
        return String.valueOf(id);
    }

    /* Pessoa inativa não autentica. */
    @Override
    public boolean isEnabled() {
        return ativo;
    }
}
