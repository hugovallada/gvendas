package com.github.hugovallada.gvendas.controlador;

import com.github.hugovallada.gvendas.dto.ClienteVendaResponseDTO;
import com.github.hugovallada.gvendas.dto.VendaRequestDTO;
import com.github.hugovallada.gvendas.servico.VendaServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Venda")
@RestController
@RequestMapping("/venda")
public class VendaControlador {

    private VendaServico vendaServico;

    @Autowired
    public VendaControlador(VendaServico vendaServico) {
        this.vendaServico = vendaServico;
    }


    @ApiOperation(value = "Listar Vendas Por Cliente", nickname = "listarVendaPorCliente")
    @GetMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCliente(
            @PathVariable Long codigoCliente
    ) {
        return ResponseEntity.ok().body(vendaServico.listarVendaPorCliente(codigoCliente));
    }

    @ApiOperation(value = "Listar Vendas por Código", nickname = "listarVendaPorCodigo")
    @GetMapping("/{codigoVenda}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCodigo(
            @PathVariable Long codigoVenda
    ) {
        return ResponseEntity.ok().body(vendaServico.listarVendaPorCodigo(codigoVenda));
    }

    @ApiOperation(value = "Salvar Vendas", nickname = "salvarCliente")
    @PostMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> salvarVenda(
            @PathVariable Long codigoCliente,
            @RequestBody @Valid VendaRequestDTO vendaRequestDTO
    ) {

        return ResponseEntity.status(HttpStatus.CREATED).body(vendaServico.salvar(codigoCliente, vendaRequestDTO));
    }


}
