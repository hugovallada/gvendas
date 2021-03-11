package com.github.hugovallada.gvendas.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

@ApiModel("Itens da Venda Retorno DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ItemVendaResponseDTO {

    @ApiModelProperty(name = "Código do item da venda")
    private Long codigo;

    @ApiModelProperty(name = "Quantidade")
    private Integer quantidade;

    @ApiModelProperty(name = "Preço da venda")
    private BigDecimal precoVendido;

    @ApiModelProperty(name = "Código do Produto")
    private Long codigoProduto;

    @ApiModelProperty(name = "Descrição do Produto")
    private String produtoDescricao;


}
