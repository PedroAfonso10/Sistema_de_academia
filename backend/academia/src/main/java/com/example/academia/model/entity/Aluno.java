package com.example.academia.model.entity;

import com.example.academia.model.enums.Papel;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
}
