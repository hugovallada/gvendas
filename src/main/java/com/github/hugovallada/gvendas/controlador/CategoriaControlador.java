package com.github.hugovallada.gvendas.controlador;

import com.github.hugovallada.gvendas.entidades.Categoria;
import com.github.hugovallada.gvendas.servico.CategoriaServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaControlador {

    private CategoriaServico servico;

    @Autowired
    public CategoriaControlador(CategoriaServico servico) {
        this.servico = servico;
    }

    @ApiOperation(value = "Listar", nickname = "listarTodas")
    @GetMapping
    public List<Categoria> listarTodas() {
        return servico.listarTodas();
    }

    @ApiOperation(value = "Listar por código", nickname = "buscarPorCódigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Categoria>> buscarPorCodigo(@PathVariable Long codigo) {
        Optional<Categoria> opt = servico.buscarPorId(codigo);
        return opt.isPresent() ? ResponseEntity.ok().body(opt) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "salvar")
    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody @Valid Categoria categoria) {
        Categoria categoriaSalva = servico.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @ApiOperation(value = "Atualizar", nickname = "atualizar")
    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria> atualizar(
            @PathVariable Long codigo,
            @RequestBody @Valid Categoria categoria
    ) {
        Categoria categoriaAtualizada = servico.atualizar(codigo, categoria);

        return ResponseEntity.ok().body(categoriaAtualizada);
    }

    @ApiOperation(value = "Deletar", nickname = "deletar")
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
        servico.deletar(codigo);
        return ResponseEntity.noContent().build();
    }
}
