package com.mateusmendanha.springboot;

import com.mateusmendanha.springboot.domain.Categoria;
import com.mateusmendanha.springboot.domain.Cidade;
import com.mateusmendanha.springboot.domain.Estado;
import com.mateusmendanha.springboot.domain.Produto;
import com.mateusmendanha.springboot.repositories.CategoriaRepository;
import com.mateusmendanha.springboot.repositories.CidadeRepository;
import com.mateusmendanha.springboot.repositories.EstadoRepository;
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

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

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

        Estado est1 = new Estado(null,"Minas Gerais");
        Estado est2 = new Estado(null,"São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2,c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
    }
}
