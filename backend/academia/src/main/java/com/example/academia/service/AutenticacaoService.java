package com.example.academia.service;

import com.example.academia.exception.*;
import com.example.academia.model.entity.ContaAcesso;
import com.example.academia.model.entity.Pessoa;
import com.example.academia.model.enums.Papel;
import com.example.academia.repository.IContaAcessoRepository;
import com.example.academia.repository.IPessoaRepository;
import com.example.academia.security.JwtService;
import com.example.academia.security.UsuarioAutenticado;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final AuthenticationManager authenticationManager;
    private final IPessoaRepository ipessoaRepository;
    private final IContaAcessoRepository icontaAcessoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(String identificador, String senha) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(identificador, senha));
            return jwtService.gerarToken((UsuarioAutenticado) auth.getPrincipal());
        } catch (BadCredentialsException | AccountStatusException e) {
            throw new InvalidCredentialsException("Credenciais inválidas.");
        }
    }

    @Transactional
    public void criarConta(UsuarioAutenticado solicitante, Long pessoaId, String senha) {
        Pessoa pessoa = ipessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada."));

        validarPermissaoSobreConta(solicitante, pessoa);
        if (!pessoa.isAtivo()) {
            throw new BusinessRuleException("Não é possível criar conta para pessoa inativa.");
        }
        criarOuRedefinir(pessoa, senha);
    }

    void criarOuRedefinir(Pessoa pessoa, String senha) {
        String hash = passwordEncoder.encode(senha);
        icontaAcessoRepository.findByPessoaId(pessoa.getId()).ifPresentOrElse(
                conta -> conta.alterarSenha(hash),
                () -> icontaAcessoRepository.save(new ContaAcesso(pessoa, hash)));
    }

    @Transactional
    public void alterarSenha(UsuarioAutenticado solicitante, String senhaAtual, String novaSenha) {
        ContaAcesso conta = icontaAcessoRepository.findByPessoaId(solicitante.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Conta de acesso não encontrada."));

        if (!passwordEncoder.matches(senhaAtual, conta.getSenhaHash())) {
            throw new InvalidDataException("Senha atual incorreta.");
        }
        conta.alterarSenha(passwordEncoder.encode(novaSenha));
    }

    /* Aluno: funcionário ou admin. Funcionário/admin: somente admin. */
    private void validarPermissaoSobreConta(UsuarioAutenticado solicitante, Pessoa alvo) {
        boolean permitido = alvo.getPapel() == Papel.ALUNO
                ? solicitante.getPapel() != Papel.ALUNO
                : solicitante.getPapel() == Papel.ADMIN;
        if (!permitido) {
            throw new AccessDeniedException("Sem permissão para gerenciar esta conta.");
        }
    }
}