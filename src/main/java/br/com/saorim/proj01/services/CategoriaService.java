package br.com.saorim.proj01.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.saorim.proj01.domain.Categoria;
import br.com.saorim.proj01.repositories.CategoriaRepository;
import br.com.saorim.proj01.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. ID: " + id + 
				", Tipo: " + Categoria.class.getName()));
	}
}
