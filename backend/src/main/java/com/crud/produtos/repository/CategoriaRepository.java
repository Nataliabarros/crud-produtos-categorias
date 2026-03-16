package com.crud.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.produtos.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}