package com.github.hugovallada.gvendas.controlador;

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
    public ResponseEntity<List<Produto>> buscarTodos(@PathVariable Long codigoCategoria) {
        List<Produto> produtos = servico.listarTodos(codigoCategoria);
        return ResponseEntity.ok().body(produtos);
    }

    @ApiOperation(value = "Buscar por código", nickname = "buscarPorCodigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Produto>> buscarProduto(
            @PathVariable Long codigoCategoria,
            @PathVariable Long codigo
    ) {
        Optional<Produto> produto = servico.buscarPorCodigo(codigo, codigoCategoria);
        return produto.isPresent() ? ResponseEntity.ok().body(produto) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "salvarProduto")
    @PostMapping
    public ResponseEntity<Produto> salvar(
            @PathVariable Long codigoCategoria,
            @RequestBody @Valid Produto produto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(servico.salvar(codigoCategoria, produto));
    }

    @ApiOperation(value = "Atualizar", nickname = "atualizarProduto")
    @PutMapping("/{codigo}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long codigoCategoria,
            @PathVariable Long codigo,
            @RequestBody @Valid Produto produto
    ) {
        return ResponseEntity.ok().body(servico.atualizar(codigoCategoria, codigo, produto));
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
