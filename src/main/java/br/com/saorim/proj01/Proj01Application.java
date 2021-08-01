package br.com.saorim.proj01;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.saorim.proj01.domain.Categoria;
import br.com.saorim.proj01.domain.Produto;
import br.com.saorim.proj01.repositories.CategoriaRepository;
import br.com.saorim.proj01.repositories.ProdutoRepository;

@SpringBootApplication
public class Proj01Application implements CommandLineRunner{
	
	@Autowired
	CategoriaRepository cr;
	
	@Autowired
	ProdutoRepository pr;

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
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		cr.saveAll(Arrays.asList(cat1, cat2));
		pr.saveAll(Arrays.asList(p1, p2, p3));
	}
}
