package com.github.hugovallada.gvendas.repositorio;

import com.github.hugovallada.gvendas.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoriaCodigo(Long codigoCategoria);

    @Query("Select prod from Produto prod where prod.codigo = :codigo and prod.categoria.codigo = :codigoCategoria")
    Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria);

    Optional<Produto> findByCategoriaCodigoAndDescricao(Long codigoCategoria, String descricao);
}
