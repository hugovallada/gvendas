package com.github.hugovallada.gvendas.dto;

import com.github.hugovallada.gvendas.entidades.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@ApiModel("Produto Retorno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDTO {

    @ApiModelProperty(value = "Código")
    private Long codigo;

    @ApiModelProperty(value = "Descrição")
    private String descricao;

    @ApiModelProperty(value = "Quantidade")
    private Integer quantidade;

    @ApiModelProperty(value = "Preço Curto")
    private BigDecimal precoCurto;

    @ApiModelProperty(value = "Preço Venda")
    private BigDecimal precoVenda;

    @ApiModelProperty(value = "Observação")
    private String observacao;

    @ApiModelProperty(value = "Categoria")
    private CategoriaResponseDTO categoria;


    public static ProdutoResponseDTO converterParaProdutoDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getCodigo(),
                produto.getDescricao(),
                produto.getQuantidade(),
                produto.getPrecoCurto(),
                produto.getPrecoVenda(),
                produto.getObservacao(),
                CategoriaResponseDTO.converterParaCategoriaDTO(produto.getCategoria()));

    }

}
