package com.github.hugovallada.gvendas.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Item Venda Request DTO")
public class ItemVendaRequestDTO {

    @ApiModelProperty(value = "Código do Produto")
    private Long codigoProduto;

    @ApiModelProperty(value = "Quantidade")
    private Integer quantidade;

    @ApiModelProperty(value = "Preço vendido")
    private BigDecimal precoVendido;


}
