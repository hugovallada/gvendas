package com.github.hugovallada.gvendas.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("Cliente da Venda Retorno DTO")
public class ClienteVendaResponseDTO {

    @ApiModelProperty(value = "Nome Cliente")
    private String nome;

    @ApiModelProperty(value = "Vendas do cliente")
    private List<VendaResponseDTO> vendaResponseDTOS;


}
