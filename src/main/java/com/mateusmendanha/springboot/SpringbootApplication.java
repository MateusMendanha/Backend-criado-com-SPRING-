package com.mateusmendanha.springboot;

import com.mateusmendanha.springboot.domain.*;
import com.mateusmendanha.springboot.domain.enums.EstadoPagamento;
import com.mateusmendanha.springboot.domain.enums.TipoCliente;
import com.mateusmendanha.springboot.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
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

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

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

        Cliente cl1 = new Cliente(null, "Maria fagundes", "maria@live.com", "0101010101", TipoCliente.PESSOAFISICA);
        cl1.getTelefones().addAll(Arrays.asList("4002-8922", "080040004"));

        Endereco end1 = new Endereco(null, "Rua dos bobos", "0", "Apto 404", "Alphaville", "38245050", cl1, c1);
        Endereco end2 = new Endereco(null, "Avenida Flores", "457", "Apto 813", "Guaruja", "38245050", cl1, c2);

        cl1.getEnderecos().addAll(Arrays.asList(end1, end2));

        clienteRepository.saveAll(Arrays.asList(cl1));
        enderecoRepository.saveAll(Arrays.asList(end1, end2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2020 22:45"), cl1, end1);

        Pedido ped2 = new Pedido(null, sdf.parse("03/04/2020 12:15"), cl1, end2);

        Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pgto1);

        Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("05/04/2020 23:59"),null);
        ped2.setPagamento(pgto2);

        cl1.getPedidos().addAll(Arrays.asList(ped1,ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));

        pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
    }
}
