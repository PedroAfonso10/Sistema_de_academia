package com.example.academia.repository;

import com.example.academia.model.entity.Funcionario;
import com.example.academia.model.enums.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> listByPerfil(Perfil perfil);
}
