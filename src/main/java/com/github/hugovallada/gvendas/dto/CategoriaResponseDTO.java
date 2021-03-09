package com.github.hugovallada.gvendas.dto;

import com.github.hugovallada.gvendas.entidades.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Categoria Retorno DTO")
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaResponseDTO {

    @ApiModelProperty(value = "Código")
    private Long codigo;

    @ApiModelProperty(value = "Nome")
    private String nome;

    public static CategoriaResponseDTO converterParaCategoriaDTO(Categoria categoria) {
        return new CategoriaResponseDTO(categoria.getCodigo(), categoria.getNome());
    }
    

}
