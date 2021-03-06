package com.github.hugovallada.gvendas.repositorio;

import com.github.hugovallada.gvendas.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nome);

}
