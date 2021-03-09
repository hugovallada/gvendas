package com.github.hugovallada.gvendas.controlador;

import com.github.hugovallada.gvendas.dto.CategoriaRequestDTO;
import com.github.hugovallada.gvendas.dto.CategoriaResponseDTO;
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
import java.util.stream.Collectors;

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
    public List<CategoriaResponseDTO> listarTodas() {
        return servico.listarTodas().stream()
                .map(categoria -> CategoriaResponseDTO.converterParaCategoriaDTO(categoria))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar por código", nickname = "buscarPorCódigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorCodigo(@PathVariable Long codigo) {
        Optional<Categoria> opt = servico.buscarPorId(codigo);
        return opt.isPresent() ? ResponseEntity.ok().body(CategoriaResponseDTO.converterParaCategoriaDTO(opt.get())) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "salvar")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@RequestBody @Valid CategoriaRequestDTO categoriaDTO) {
        Categoria categoriaSalva = servico.salvar(categoriaDTO.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDTO.converterParaCategoriaDTO(categoriaSalva));
    }

    @ApiOperation(value = "Atualizar", nickname = "atualizar")
    @PutMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(
            @PathVariable Long codigo,
            @RequestBody @Valid CategoriaRequestDTO categoriaDTO
    ) {
        Categoria categoriaAtualizada = servico.atualizar(codigo, categoriaDTO.converterParaEntidade(codigo));

        return ResponseEntity.ok().body(CategoriaResponseDTO.converterParaCategoriaDTO(categoriaAtualizada));
    }

    @ApiOperation(value = "Deletar", nickname = "deletar")
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
        servico.deletar(codigo);
        return ResponseEntity.noContent().build();
    }
}
