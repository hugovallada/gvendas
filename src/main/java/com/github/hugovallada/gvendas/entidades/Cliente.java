package com.github.hugovallada.gvendas.entidades;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    private String telefone;

    private boolean ativo;

    @Embedded
    private Endereco endereco;

    public boolean getAtivo() {
        return ativo;
    }
}
