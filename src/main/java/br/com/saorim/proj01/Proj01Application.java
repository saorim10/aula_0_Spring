package br.com.saorim.proj01;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.saorim.proj01.domain.Categoria;
import br.com.saorim.proj01.repositories.CategoriaRepository;

@SpringBootApplication
public class Proj01Application implements CommandLineRunner{
	
	@Autowired
	CategoriaRepository cr;

	public static void main(String[] args) {
		SpringApplication.run(Proj01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		cr.saveAll(Arrays.asList(cat1, cat2));
	}
}
