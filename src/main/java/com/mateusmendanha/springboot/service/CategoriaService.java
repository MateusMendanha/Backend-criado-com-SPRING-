package com.mateusmendanha.springboot.service;

// Class que faz o serviço de busca de categorias

import com.mateusmendanha.springboot.domain.Categoria;
import com.mateusmendanha.springboot.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElse(null);
    }
}
