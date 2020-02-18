package com.phanet.cursomc.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phanet.cursomc.domain.Pedido;
import com.phanet.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service; 
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		Pedido obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	/*
	//sem banco
	public List<Pedido> Listar() {
		Pedido cat1 = new Pedido(1,"Informática");         
		Pedido cat2 = new Pedido(2,"Escritório");
		
		List<Pedido> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		
		return lista;
		
		//return "Rest está funcionando!";	
	}*/
	
	
	
}
