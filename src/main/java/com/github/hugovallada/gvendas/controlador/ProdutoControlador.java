package com.github.hugovallada.gvendas.controlador;

import com.github.hugovallada.gvendas.dto.ProdutoRequestDTO;
import com.github.hugovallada.gvendas.dto.ProdutoResponseDTO;
import com.github.hugovallada.gvendas.entidades.Produto;
import com.github.hugovallada.gvendas.servico.ProdutoServico;
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

@Api(tags = "Produtos")
@RestController
@RequestMapping("/categoria/{codigoCategoria}/produto")
public class ProdutoControlador {

    private ProdutoServico servico;

    @Autowired
    public ProdutoControlador(ProdutoServico servico) {
        this.servico = servico;
    }

    @ApiOperation(value = "Listar", nickname = "listarTodasProduto")
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> buscarTodos(@PathVariable Long codigoCategoria) {
        List<ProdutoResponseDTO> produtos = servico.listarTodos(codigoCategoria)
                .stream()
                .map(produto -> ProdutoResponseDTO.converterParaProdutoDTO(produto))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(produtos);
    }

    @ApiOperation(value = "Buscar por código", nickname = "buscarPorCodigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<ProdutoResponseDTO> buscarProduto(
            @PathVariable Long codigoCategoria,
            @PathVariable Long codigo
    ) {
        Optional<Produto> produto = servico.buscarPorCodigo(codigo, codigoCategoria);
        return produto.isPresent() ? ResponseEntity.ok().body(ProdutoResponseDTO.converterParaProdutoDTO(produto.get())) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "salvarProduto")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(
            @PathVariable Long codigoCategoria,
            @RequestBody @Valid ProdutoRequestDTO dto) {

        Produto prodSalvo = servico.salvar(codigoCategoria, dto.converterParaEntidade(codigoCategoria));

        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.converterParaProdutoDTO(prodSalvo));
    }

    @ApiOperation(value = "Atualizar", nickname = "atualizarProduto")
    @PutMapping("/{codigo}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable Long codigoCategoria,
            @PathVariable Long codigo,
            @RequestBody @Valid ProdutoRequestDTO dto
    ) {
        return ResponseEntity.ok().body(ProdutoResponseDTO.converterParaProdutoDTO(servico.atualizar(codigoCategoria, codigo, dto.converterParaEntidade(codigoCategoria, codigo))));
    }

    @ApiOperation(value = "Deletar", nickname = "deletarProduto")
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long codigoCategoria,
            @PathVariable Long codigo
    ) {
        servico.delete(codigoCategoria, codigo);
        return ResponseEntity.noContent().build();
    }
}
