package com.github.hugovallada.gvendas.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;

    private Integer numero;

    private String complemento;

    private String bairro;

    private String cep;

    private String cidade;

    private String estado;
}
