package com.example.academia.model.entity;

import com.example.academia.model.enums.Papel;
import com.example.academia.model.enums.Perfil;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "funcionario")
@Getter
@SuperBuilder
@PrimaryKeyJoinColumn(name = "pessoa_id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Funcionario extends Pessoa{

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false, length = 30)
    private Perfil perfil;

    @Column(name = "data_admissao", nullable = false)
    private LocalDate dataAdmissao;

    @PrePersist
    protected void registrarDataAdmissao() {
        if (this.dataAdmissao == null) {
            this.dataAdmissao = LocalDate.now();
        }
    }

    public Papel getPapel(){
        return switch (perfil){
                case FUNCIONARIO -> Papel.FUNCIONARIO;
                case ADMIN -> Papel.ADMIN;
        };
    }

    public boolean isAdmin(){
        return getPapel() == Papel.ADMIN;
    }


}
