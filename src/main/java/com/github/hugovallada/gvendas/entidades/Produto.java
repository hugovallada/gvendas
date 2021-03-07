package com.github.hugovallada.gvendas.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "descricao", nullable = false)
    @NotBlank(message = "A descricao e obrigatoria")
    @Length(min = 3, max = 50, message = "A descricao deve ter entre 3 e 50 caracteres")
    private String descricao;

    @Column(name = "quantidade", nullable = false)
    @NotNull(message = "A quantidade deve ser informada")
    private Integer quantidade;

    @Column(name = "preco_curto", nullable = false)
    @NotNull(message = "O preco de custo deve ser informado")
    private BigDecimal precoCurto;

    @Column(name = "preco_venda", nullable = false)
    @NotNull(message = "O preco de venda deve ser informado")
    private BigDecimal precoVenda;

    @Column(name = "observacao", nullable = true)
    @Length(max = 500, message = "A observacao deve ter no máximo 500 caracteres")
    private String observacao;

    @ManyToOne
    @JoinColumn(
            name = "codigo_categoria",
            referencedColumnName = "codigo"
    )
    @NotNull(message = "A categoria é obrigatória")
    private Categoria categoria;

}
