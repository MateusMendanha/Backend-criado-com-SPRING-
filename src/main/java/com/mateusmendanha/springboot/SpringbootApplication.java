package com.mateusmendanha.springboot;

import com.mateusmendanha.springboot.domain.Categoria;
import com.mateusmendanha.springboot.domain.Produto;
import com.mateusmendanha.springboot.repositories.CategoriaRepository;
import com.mateusmendanha.springboot.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computador Gamer", 4000.99);
        Produto p2 = new Produto(null, "Impressora Multifuncional", 1500.19);
        Produto p3 = new Produto(null, "Mouse Gamer", 800.15);

        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll((Arrays.asList(cat1)));
        p2.getCategorias().addAll((Arrays.asList(cat1,cat2)));
        p3.getCategorias().addAll((Arrays.asList(cat1)));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll((Arrays.asList(p1,p2,p3)));
    }
}
