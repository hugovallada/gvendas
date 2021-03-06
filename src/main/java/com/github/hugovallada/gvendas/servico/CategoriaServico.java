package com.github.hugovallada.gvendas.servico;

import com.github.hugovallada.gvendas.entidades.Categoria;
import com.github.hugovallada.gvendas.excecao.RegraNegocioException;
import com.github.hugovallada.gvendas.repositorio.CategoriaRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        validarCategoria(categoria);
        return repositorio.save(categoria);
    }

    public Categoria atualizar(Long codigo, Categoria categoria) {
        Categoria cat = validarCategoriaExiste(codigo);
        validarCategoria(categoria);

        BeanUtils.copyProperties(categoria, cat, "codigo");

        return repositorio.save(cat);
    }

    public void deletar(Long codigo) {
        validarCategoriaExiste(codigo);

        repositorio.deleteById(codigo);
    }

    private Categoria validarCategoriaExiste(Long codigo) {
        Optional<Categoria> opt = buscarPorId(codigo);

        if (opt.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        return opt.get();
    }

    private void validarCategoria(Categoria categoria) {
        Optional<Categoria> categoriaPorNome = repositorio.findByNome(categoria.getNome());


        if (categoriaPorNome.isPresent() && categoriaPorNome.get().getCodigo() != categoria.getCodigo()) {
            throw new RegraNegocioException(String.format("A categoria %s ja esta cadastrada", categoria.getNome().toUpperCase()));
        }
    }
}
