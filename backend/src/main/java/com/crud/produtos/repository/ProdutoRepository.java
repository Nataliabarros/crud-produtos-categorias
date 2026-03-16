package com.crud.produtos.repository;

import com.crud.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}