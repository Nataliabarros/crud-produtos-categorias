package com.crud.produtos.controller;

import com.crud.produtos.model.Categoria;
import com.crud.produtos.repository.CategoriaRepository;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    @DeleteMapping("/{id}")
    public void deletarCategoria(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
    }
}