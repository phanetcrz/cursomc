package com.phanet.cursomc.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanet.cursomc.domain.Categoria;
import com.phanet.cursomc.repositories.CategoriaRepository;
import com.phanet.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	public CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id:" + id + ", Tipo: " + Categoria.class.getName()));		
	}
}
