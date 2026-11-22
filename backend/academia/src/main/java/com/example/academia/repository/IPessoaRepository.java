package com.example.academia.repository;

import com.example.academia.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
