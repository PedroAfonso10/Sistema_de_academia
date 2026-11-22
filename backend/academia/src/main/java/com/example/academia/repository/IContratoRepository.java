package com.example.academia.repository;

import com.example.academia.model.entity.Contrato;
import com.example.academia.model.enums.StatusContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContratoRepository extends JpaRepository<Contrato, Long> {
    boolean existsByAlunoIdAndStatusContrato(Long alunoId, StatusContrato statusContrato);

    boolean existsByPlanoIdAndStatusContrato(Long planoId, StatusContrato statusContrato);

    // Atalhos para chamar no Service
    default boolean existsContratoAtivoPorAluno(Long alunoId) {
        return existsByAlunoIdAndStatusContrato(alunoId, StatusContrato.ATIVO);
    }

    default boolean existsContratoAtivoPorPlano(Long planoId) {
        return existsByPlanoIdAndStatusContrato(planoId, StatusContrato.ATIVO);
    }
}
