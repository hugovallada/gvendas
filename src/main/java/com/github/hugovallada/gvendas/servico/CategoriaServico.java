package com.github.hugovallada.gvendas.servico;

import com.github.hugovallada.gvendas.entidades.Categoria;
import com.github.hugovallada.gvendas.repositorio.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServico {

    private CategoriaRepositorio repositorio;

    @Autowired
    public CategoriaServico(CategoriaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Categoria> listarTodas() {
        return repositorio.findAll();
    }

    public Optional<Categoria> buscarPorId(Long codigo) {
        return repositorio.findById(codigo);
    }

    public Categoria salvar(Categoria categoria) {
        return repositorio.save(categoria);
    }
}
