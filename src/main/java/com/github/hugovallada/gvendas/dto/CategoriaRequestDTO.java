package com.github.hugovallada.gvendas.dto;

import com.github.hugovallada.gvendas.entidades.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Categoria Requisição DTO")
public class CategoriaRequestDTO {

    @ApiModelProperty(value = "Nome")
    @NotBlank(message = "O nome e obrigatorio")
    @Length(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String nome;
    
    public Categoria converterParaEntidade() {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);

        return categoria;
    }

    public Categoria converterParaEntidade(Long codigo) {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoria.setCodigo(codigo);

        return categoria;
    }

}
