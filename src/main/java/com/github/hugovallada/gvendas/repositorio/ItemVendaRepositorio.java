package com.github.hugovallada.gvendas.repositorio;

import com.github.hugovallada.gvendas.entidades.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemVendaRepositorio extends JpaRepository<ItemVenda, Long> {
    List<ItemVenda> findByVendaCodigo(Long vendaCodigo);
}
