package com.github.hugovallada.gvendas.controlador;

import com.github.hugovallada.gvendas.dto.ClienteRequestDTO;
import com.github.hugovallada.gvendas.dto.ClienteResponseDTO;
import com.github.hugovallada.gvendas.entidades.Cliente;
import com.github.hugovallada.gvendas.servico.ClienteServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags = "Cliente")
@RestController
@RequestMapping("/cliente")
public class ClienteControlador {

    private ClienteServico servico;

    @Autowired
    public ClienteControlador(ClienteServico servico) {
        this.servico = servico;
    }

    @ApiOperation(value = "Listar", nickname = "listarTodosClientes")
    @GetMapping
    public List<ClienteResponseDTO> buscarTodos() {
        return servico.buscarTodos().stream().map(cliente -> ClienteResponseDTO.converterParaClienteDTO(cliente)).collect(Collectors.toList());
    }

    @ApiOperation(value = "Buscar por Código", nickname = "listarClientePorCodigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> buscarPorCodigo(@PathVariable Long codigo) {
        Optional<Cliente> clienteOptional = servico.buscarPorCodigo(codigo);

        return clienteOptional.isPresent() ? ResponseEntity.ok().body(ClienteResponseDTO.converterParaClienteDTO(clienteOptional.get())) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar Cliente", nickname = "salvarCliente")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody @Valid ClienteRequestDTO clienteDTO) {
        Cliente savedCliente = servico.salvar(clienteDTO.converterParaEntidade());

        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDTO.converterParaClienteDTO(savedCliente));
    }

    @ApiOperation(value = "Atualizar Cliente", nickname = "atualizarCliente")
    @PutMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> atualizar(
            @PathVariable Long codigo,
            @RequestBody @Valid ClienteRequestDTO clienteDTO
    ) {
        Cliente clienteSalvo = servico.atualizar(codigo, clienteDTO.converterParaEntidade(codigo));

        return ResponseEntity.ok().body(ClienteResponseDTO.converterParaClienteDTO(clienteSalvo));
    }

    @ApiOperation(value = "Deletar Cliente", nickname = "deletarCliente")
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
        servico.deletar(codigo);
        return ResponseEntity.noContent().build();
    }

}
