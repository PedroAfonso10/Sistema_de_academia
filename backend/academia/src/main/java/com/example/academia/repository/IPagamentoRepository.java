package com.example.academia.repository;

import com.example.academia.model.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByContratoId(Long contratoId);
}
