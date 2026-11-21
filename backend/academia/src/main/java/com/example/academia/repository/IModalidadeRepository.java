package com.example.academia.repository;

import com.example.academia.model.entity.Modalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IModalidadeRepository extends JpaRepository<Modalidade, Long> {
}
