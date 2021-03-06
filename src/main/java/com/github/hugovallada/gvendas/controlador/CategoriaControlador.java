package com.github.hugovallada.gvendas.controlador;

import com.github.hugovallada.gvendas.entidades.Categoria;
import com.github.hugovallada.gvendas.servico.CategoriaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaControlador {

    private CategoriaServico servico;

    @Autowired
    public CategoriaControlador(CategoriaServico servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<Categoria> listarTodas() {
        return servico.listarTodas();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Categoria>> buscarPorCodigo(@PathVariable Long codigo) {
        Optional<Categoria> opt = servico.buscarPorId(codigo);
        return opt.isPresent() ? ResponseEntity.ok().body(opt) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Categoria> salvar(@RequestBody @Valid Categoria categoria) {
        Categoria categoriaSalva = servico.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }
}
