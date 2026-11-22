package com.example.academia.repository;

import com.example.academia.model.entity.ContaAcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IContaAcessoRepository extends JpaRepository<ContaAcesso, Long> {
    Optional<ContaAcesso> findByPessoaId(Long pessoaId);
}
