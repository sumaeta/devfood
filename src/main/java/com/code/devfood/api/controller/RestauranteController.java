package com.code.devfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.devfood.domain.model.Restaurante;
import com.code.devfood.domain.service.RestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	private final RestauranteService service;
	
	@Autowired
	public RestauranteController(RestauranteService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<Restaurante>> listar(){
		List<Restaurante> restaurantes = this.service.listar();
		return ResponseEntity.ok(restaurantes);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id){
		Restaurante restaurantes = this.service.buscar(id);
		return ResponseEntity.ok(restaurantes);
	}
}
