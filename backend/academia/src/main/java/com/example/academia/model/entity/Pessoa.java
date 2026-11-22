package com.example.academia.model.entity;

import com.example.academia.model.enums.Papel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Past
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Builder.Default
    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    public abstract Papel getPapel ();

    public void ativar(){
        this.ativo = true;
    }

    public void desativar(){ this.ativo = false; }

    public void atualizarDados(String nome, String email, LocalDate dataNascimento){
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    @PrePersist
    @PreUpdate
    protected void normalizar() {
        if (this.email != null) {
            this.email = this.email.trim().toLowerCase();
        }
        if (this.cpf != null) {
            this.cpf = this.cpf.replaceAll("\\D", "");
        }
    }
}
