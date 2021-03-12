package com.github.hugovallada.gvendas.repositorio;

import com.github.hugovallada.gvendas.entidades.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepositorio extends JpaRepository<Venda, Long> {

    List<Venda> findByClienteCodigo(Long codigoCliente);
}
