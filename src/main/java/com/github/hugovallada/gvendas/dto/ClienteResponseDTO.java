package com.github.hugovallada.gvendas.dto;

import com.github.hugovallada.gvendas.entidades.Cliente;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel("Cliente Retorno DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO {


    @ApiModelProperty(value = "Código")
    private Long codigo;

    @ApiModelProperty(value = "Nome")
    private String nome;

    @ApiModelProperty(value = "Telefone")
    private String telefone;

    @ApiModelProperty(value = "Ativo")
    private boolean ativo;

    private EnderecoResponseDTO endereco;

    public static ClienteResponseDTO converterParaClienteDTO(Cliente cliente) {
        EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO(cliente.getEndereco().getLogradouro(), cliente.getEndereco().getNumero(),
                cliente.getEndereco().getComplemento(), cliente.getEndereco().getBairro(), cliente.getEndereco().getCep(),
                cliente.getEndereco().getCidade(), cliente.getEndereco().getEstado());

        return new ClienteResponseDTO(cliente.getCodigo(), cliente.getNome(), cliente.getTelefone(), cliente.getAtivo(), enderecoResponseDTO);
        
    }

}
