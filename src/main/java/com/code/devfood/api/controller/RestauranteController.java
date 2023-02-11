package com.code.devfood.api.controller;

import java.math.BigDecimal;
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
	public Restaurante buscar(@PathVariable Long id) {
		return this.service.buscar(id);
	}

	@PostMapping
	public ResponseEntity<Restaurante> salvar(@RequestBody Restaurante restaurante) {
		this.service.salvar(restaurante);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping(value = "/{id}")
	public Restaurante atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante){
		return this.service.atualizar(id, restaurante);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos){
		Restaurante restauranteAtual = this.buscar(id);
		Restaurante retorno = this.service.atualizarParcialmente(campos, restauranteAtual);
		return ResponseEntity.ok(retorno);
	}

	@GetMapping(value = "/taxa-frete")
	public List<Restaurante> buscaPorTaxaFrete(BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		return service.buscaPorTaxa(taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping(value = "/por-nome")
	public List<Restaurante> buscaPorNome(Long cozinhaId, String nome){
		return  this.service.buscaPorNome(cozinhaId, nome);
	}
	
	@GetMapping(value = "/find")
	public ResponseEntity<List<Restaurante>> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		List<Restaurante> retorno = service.find(nome, taxaFreteInicial, taxaFreteFinal);
		return ResponseEntity.ok(retorno);
	}
}
