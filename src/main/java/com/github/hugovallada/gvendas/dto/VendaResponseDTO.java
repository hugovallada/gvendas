package com.github.hugovallada.gvendas.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@ApiModel("Venda Retorno DTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VendaResponseDTO {

    @ApiModelProperty(name = "Código da Venda")
    private Long codigo;

    @ApiModelProperty(name = "Data da venda")
    private LocalDate data;

    @ApiModelProperty(name = "Itens da venda")
    private List<ItemVendaResponseDTO> itemVendaResponseDTOS;

}
