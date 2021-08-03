package br.com.saorim.proj01.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.saorim.proj01.domain.Cliente;
import br.com.saorim.proj01.repositories.ClienteRepository;
import br.com.saorim.proj01.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. ID: " + id + 
				", Tipo: " + Cliente.class.getName()));
	}
}
