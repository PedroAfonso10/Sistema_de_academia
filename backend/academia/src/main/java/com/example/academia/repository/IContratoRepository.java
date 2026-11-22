package com.example.academia.repository;

import com.example.academia.model.entity.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContratoRepository extends JpaRepository<Contrato, Long> {
    boolean existsByAlunoIdAndAtivoTrue(Long alunoId);
    boolean existsByPlanoIdAndAtivoTrue(Long planoId);
}
