package com.github.hugovallada.gvendas.servico;

import com.github.hugovallada.gvendas.entidades.Produto;
import com.github.hugovallada.gvendas.excecao.RegraNegocioException;
import com.github.hugovallada.gvendas.repositorio.ProdutoRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServico {

    private ProdutoRepositorio repositorio;
    private CategoriaServico categoriaServico;

    @Autowired
    public ProdutoServico(ProdutoRepositorio repositorio, CategoriaServico servico) {
        this.repositorio = repositorio;
        this.categoriaServico = servico;
    }

    public List<Produto> listarTodos() {
        return repositorio.findAll();
    }

    public Optional<Produto> buscarPorCodigo(Long codigo) {
        return repositorio.findById(codigo);
    }

    public List<Produto> listarTodos(Long codigoCategoria) {
        return repositorio.findByCategoriaCodigo(codigoCategoria);
    }

    public Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria) {
        return repositorio.buscarPorCodigo(codigo, codigoCategoria);
    }

    public Produto salvar(Long codigoCategoria, Produto produto) {
        validarCategoriaDoProdutoExiste(codigoCategoria);
        validarProdutoDuplicado(produto);
        return repositorio.save(produto);
    }

    public Produto atualizar(Long codigoCategoria, Long codigoProduto, Produto produto) {
        Produto produtoSalvar = validarProdutoExiste(codigoCategoria, codigoProduto);
        validarCategoriaDoProdutoExiste(codigoCategoria);
        validarProdutoDuplicado(produto);

        BeanUtils.copyProperties(produto, produtoSalvar, "codigo");

        return repositorio.save(produtoSalvar);
    }

    public void delete(Long codigoCategoria, Long codigoProduto) {
        Produto produto = validarProdutoExiste(codigoCategoria, codigoProduto);
        repositorio.delete(produto);
    }

    private void validarCategoriaDoProdutoExiste(Long codigoCategoria) {

        if (codigoCategoria == null) {
            throw new RegraNegocioException("A categoria não pode ser nula");
        }

        if (categoriaServico.buscarPorId(codigoCategoria).isEmpty()) {
            throw new RegraNegocioException(String.format("A categoria de código %s não existe no cadastro", codigoCategoria));
        }
    }

    private void validarProdutoDuplicado(Produto produto) {
        Optional<Produto> produtoPorDescricao = repositorio.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao());
        if (produtoPorDescricao.isPresent() && produtoPorDescricao.get().getCodigo() != produto.getCodigo()) {
            throw new RegraNegocioException(
                    String.format("Já existe um produto com a descricão %s na categoria de código %s",
                            produto.getDescricao(), produto.getCategoria().getCodigo())
            );
        }
    }

    private Produto validarProdutoExiste(Long codigoCategoria, Long codigoProduto) {
        Optional<Produto> encontrado = buscarPorCodigo(codigoProduto, codigoCategoria);

        if (encontrado.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        return encontrado.get();
    }
}
