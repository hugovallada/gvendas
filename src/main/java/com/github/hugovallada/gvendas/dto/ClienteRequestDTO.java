package com.github.hugovallada.gvendas.dto;

import com.github.hugovallada.gvendas.entidades.Cliente;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel("Cliente Requisição DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO {

    @ApiModelProperty(value = "Nome")
    @NotBlank(message = "O nome não deve estar em branco")
    @Length(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @ApiModelProperty(value = "Telefone")
    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "\\([\\d]{2}\\)[\\d]{5}[- .][\\d]{4}", message = "O formato do telefone está incorreto")
    private String telefone;

    @ApiModelProperty(value = "Ativo")
    @NotNull(message = "Informe se o usuário está ativo")
    private boolean ativo;

    @Valid
    private EnderecoRequestDTO endereco;

    public Cliente converterParaEntidade() {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEndereco(endereco.converterParaEndereco());
        cliente.setTelefone(telefone);
        cliente.setAtivo(ativo);

        return cliente;
    }

    public Cliente converterParaEntidade(Long codigo) {
        Cliente cliente = new Cliente();
        cliente.setCodigo(codigo);
        cliente.setNome(nome);
        cliente.setEndereco(endereco.converterParaEndereco());
        cliente.setTelefone(telefone);
        cliente.setAtivo(ativo);

        return cliente;
    }
}
