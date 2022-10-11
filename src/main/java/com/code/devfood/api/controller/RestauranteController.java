package com.code.devfood.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.devfood.domain.model.Restaurante;
import com.code.devfood.domain.service.RestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	private final RestauranteService service;

	public RestauranteController(RestauranteService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Restaurante>> listar() {
		List<Restaurante> restaurantes = this.service.listar();
		return ResponseEntity.ok(restaurantes);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Restaurante restaurantes = this.service.buscar(id);
		return ResponseEntity.ok(restaurantes);
	}

	@PostMapping
	public ResponseEntity<Restaurante> salvar(@RequestBody Restaurante restaurante) {
		this.service.salvar(restaurante);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Restaurante> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante){
		Restaurante retorno = this.service.atualizar(id, restaurante);
		return ResponseEntity.ok(retorno);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos){
		Restaurante restauranteAtual = this.buscar(id).getBody();
		Restaurante retorno = this.service.atualizarParcialmente(campos, restauranteAtual);
		return ResponseEntity.ok(retorno);
	}

	
}
