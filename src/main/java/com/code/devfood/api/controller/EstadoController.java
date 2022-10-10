package com.code.devfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.devfood.domain.model.Estado;
import com.code.devfood.domain.service.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

	private final EstadoService service;
	
	@Autowired
	public EstadoController(EstadoService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<Estado>> listar(){
		List<Estado> estados = service.listar();
		return ResponseEntity.ok(estados);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Estado> buscar(@PathVariable Long id){
		Estado retorno = this.service.buscar(id);
		return ResponseEntity.ok(retorno);
	}
	
	@PostMapping
	public ResponseEntity<Estado> salvar(@RequestBody Estado estado){
		Estado retorno = this.service.salvar(estado);
		return ResponseEntity.ok(retorno);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Estado> remover(@PathVariable Long id){
		this.service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody Estado estado){
		Estado retorno = this.service.atualizar(id, estado);
		return ResponseEntity.ok().body(retorno);
	}
}
