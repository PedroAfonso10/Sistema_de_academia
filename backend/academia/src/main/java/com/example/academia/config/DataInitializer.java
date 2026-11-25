package com.example.academia.config;

import com.example.academia.model.entity.ContaAcesso;
import com.example.academia.model.entity.Funcionario;
import com.example.academia.model.enums.Perfil;
import com.example.academia.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/* Configura o Admin inicial */

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final IPessoaRepository ipessoaRepository;
    private final IFuncionarioRepository ifuncionarioRepository;
    private final IContaAcessoRepository icontaAcessoRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.senha}")
    private String senhaAdmin;

    @Value("${app.admin.cpf}")
    private String cpf;

    @Value("${app.admin.email}")
    private String email;

    @Override
    @Transactional
    public void run(String... args) {
        if (ipessoaRepository.existsByCpf(cpf)) {
            return;
        }
        Funcionario admin = Funcionario.builder()
                .nome("Administrador")
                .cpf(cpf)
                .email(email)
                .dataNascimento(LocalDate.of(2000, 10, 10))
                .perfil(Perfil.ADMIN)
                .build();
        ifuncionarioRepository.save(admin);
        icontaAcessoRepository.save(new ContaAcesso(admin, passwordEncoder.encode(senhaAdmin)));
    }
}
