package com.example.academia.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "conta_acesso")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContaAcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pessoa_id", nullable = false, unique = true)
    private Pessoa pessoa;

    @Column(name = "senha_hash", nullable = false, length = 100)
    private String senhaHash;

    public ContaAcesso(Pessoa pessoa, String senhaHash) {
        this.pessoa = pessoa;
        this.senhaHash = senhaHash;
    }

    public void alterarSenha(String novoHash){
        this.senhaHash = novoHash;
    }
}
