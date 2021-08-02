package br.com.saorim.proj01;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.saorim.proj01.domain.Categoria;
import br.com.saorim.proj01.domain.Cidade;
import br.com.saorim.proj01.domain.Estado;
import br.com.saorim.proj01.domain.Produto;
import br.com.saorim.proj01.repositories.CategoriaRepository;
import br.com.saorim.proj01.repositories.CidadeRepository;
import br.com.saorim.proj01.repositories.EstadoRepository;
import br.com.saorim.proj01.repositories.ProdutoRepository;

@SpringBootApplication
public class Proj01Application implements CommandLineRunner{
	
	@Autowired
	CategoriaRepository categR;
	
	@Autowired
	ProdutoRepository prodR;
	
	@Autowired
	EstadoRepository estR;
	
	@Autowired
	CidadeRepository cidR;

	public static void main(String[] args) {
		SpringApplication.run(Proj01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		
		
		categR.saveAll(Arrays.asList(cat1, cat2));
		prodR.saveAll(Arrays.asList(p1, p2, p3));
		estR.saveAll(Arrays.asList(est1, est2));
		cidR.saveAll(Arrays.asList(c1, c2, c3));
	}
}
