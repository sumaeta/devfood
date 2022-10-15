package com.code.devfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.devfood.domain.model.Cozinha;
import com.code.devfood.domain.model.Restaurante;
import com.code.devfood.domain.repository.CozinhaRepository;
import com.code.devfood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/restaurantes")
public class TesteController {

	private final RestauranteRepository repository;
	private final CozinhaRepository cozinha;
	
	public TesteController(RestauranteRepository repository, CozinhaRepository cozinha) {
		this.repository = repository;
		this.cozinha = cozinha;
	}
	
	@GetMapping("/frete-gratis")
	public List<Restaurante> lista(String nome){
		return repository.findComFreteGratis(nome);
	}
	
	@GetMapping("/primeiro")
	public Optional<Restaurante> busca(){
		return repository.buscarPrimeiro();
	}
	
	@GetMapping("/primeiro/cozinha")
	public Optional<Cozinha> buscaCozinha(){
		return cozinha.buscarPrimeiro();
	}
}
