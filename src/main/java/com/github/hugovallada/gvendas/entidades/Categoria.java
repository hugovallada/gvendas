package com.github.hugovallada.gvendas.entidades;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;


    @Column(name = "nome", nullable = false)
    @NotBlank(message = "A categoria deve ter um nome")
    @Length(min = 3, max = 50, message = "A categoria deve ter entre 3 e 50 caracteres")
    private String nome;
}
