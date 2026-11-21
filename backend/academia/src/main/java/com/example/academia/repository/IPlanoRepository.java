package com.example.academia.repository;

import com.example.academia.model.entity.Plano;
import com.example.academia.model.entity.PlanoModalidadeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlanoRepository extends JpaRepository<Plano, PlanoModalidadeId> {
}
