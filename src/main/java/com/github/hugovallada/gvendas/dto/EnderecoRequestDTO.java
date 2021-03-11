package com.github.hugovallada.gvendas.dto;

import com.github.hugovallada.gvendas.entidades.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel("Endereço Requisição DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDTO {

    @ApiModelProperty(value = "Logradouro")
    @Length(min = 3, max = 30, message = "O tamanho do logradouro deve ser entre 3 e 30 caracteres")
    private String logradouro;

    @ApiModelProperty(value = "Número")
    @NotNull
    private Integer numero;

    @ApiModelProperty(value = "Complemento")
    @Length(max = 30, message = "O complemento deve ter no máximo 30 caracteres")
    private String complemento;

    @ApiModelProperty(value = "Bairro")
    @Length(max = 30, message = "O bairro deve ter no máximo de 30 caracteres")
    private String bairro;

    @ApiModelProperty(value = "Cep")
    @Pattern(regexp = "[\\d]{5}-[\\d]{3}", message = "O formato do CEP está invalido")
    private String cep;

    @ApiModelProperty(value = "Cidade")
    @Length(max = 30, message = "A cidade deve ter no máximo 30 caracteres")
    private String cidade;

    @ApiModelProperty(value = "Estado")
    @Length(max = 30, message = "O estádo deve ter no máximo 30 caracteres")
    private String estado;

    public Endereco converterParaEndereco() {
        return new Endereco(logradouro, numero, complemento, bairro, cep, cidade, estado);
    }
}
