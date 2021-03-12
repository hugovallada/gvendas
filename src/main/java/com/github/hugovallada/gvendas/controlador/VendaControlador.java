package com.github.hugovallada.gvendas.controlador;

import com.github.hugovallada.gvendas.dto.ClienteVendaResponseDTO;
import com.github.hugovallada.gvendas.servico.VendaServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
