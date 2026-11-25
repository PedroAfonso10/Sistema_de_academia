package com.example.academia.security;

import com.example.academia.model.entity.ContaAcesso;
import com.example.academia.model.entity.Pessoa;
import com.example.academia.repository.IContaAcessoRepository;
import com.example.academia.repository.IPessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/* Chamado pelo Spring no login. O "username" é o e-mail ou o CPF. */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IPessoaRepository ipessoaRepository;
    private final IContaAcessoRepository icontaAcessoRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String identificador) throws UsernameNotFoundException {
        Pessoa pessoa = buscarPessoa(identificador)
                .orElseThrow(() -> new UsernameNotFoundException("Credenciais inválidas."));
        ContaAcesso conta = icontaAcessoRepository.findByPessoaId(pessoa.getId())
                .orElseThrow(() -> new UsernameNotFoundException("Credenciais inválidas."));

        return UsuarioAutenticado.deConta(pessoa, conta.getSenhaHash());
    }

    private Optional<Pessoa> buscarPessoa(String identificador) {
        if (identificador == null || identificador.isBlank()) {
            return Optional.empty();
        }
        String valor = identificador.trim();
        return valor.contains("@")
                ? ipessoaRepository.findByEmail(valor.toLowerCase())
                : ipessoaRepository.findByCpf(valor.replaceAll("\\D", ""));
    }
}
