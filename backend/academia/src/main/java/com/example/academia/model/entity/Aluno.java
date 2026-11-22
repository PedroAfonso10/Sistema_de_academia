package com.example.academia.model.entity;

import com.example.academia.model.enums.Papel;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Year;

@Entity
@Table(name = "aluno")
@Getter
@SuperBuilder
@PrimaryKeyJoinColumn(name = "pessoa_id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Aluno extends Pessoa{

    @Column(name = "matricula", nullable = false, unique = true, length = 20)
    private String matricula;

    public Papel getPapel(){
        return Papel.ALUNO;
    }

    public void gerarMatricula() {
        this.matricula = Year.now().getValue() + String.format("%05d", this.getId());
    }
}
