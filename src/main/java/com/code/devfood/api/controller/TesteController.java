package com.code.devfood.api.controller;

import static com.code.devfood.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.code.devfood.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.devfood.domain.model.Restaurante;
import com.code.devfood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/restaurantes")
public class TesteController {

	private final RestauranteRepository repository;
	
	public TesteController(RestauranteRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/frete-gratis")
	public List<Restaurante> lista(String nome){
		return repository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}
}
