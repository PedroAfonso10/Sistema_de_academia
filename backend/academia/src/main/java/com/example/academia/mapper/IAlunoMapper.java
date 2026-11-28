package com.example.academia.mapper;

import com.example.academia.dto.request.AlunoRequestDTO;
import com.example.academia.dto.response.AlunoResponseDTO;
import com.example.academia.model.entity.Aluno;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IAlunoMapper {
    Aluno toEntity(AlunoRequestDTO alunoRequestDTO);
    AlunoResponseDTO toResponse(Aluno aluno);
    List<AlunoResponseDTO> toResponseAll(List<Aluno> alunos);
}
