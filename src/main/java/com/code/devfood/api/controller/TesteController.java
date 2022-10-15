package com.code.devfood.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.devfood.domain.model.Restaurante;
import com.code.devfood.domain.repository.RestauranteRepository;
import com.code.devfood.infrastructure.repository.spec.RestauranteComFreteGratisEpec;
import com.code.devfood.infrastructure.repository.spec.RestauranteComNomeSemelhanteEpec;

@RestController
@RequestMapping("/restaurantes")
public class TesteController {

	private final RestauranteRepository repository;
	
	public TesteController(RestauranteRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/frete-gratis")
	public List<Restaurante> lista(String nome){
		var comFreteGratis = new RestauranteComFreteGratisEpec();
		var comNomeSemelhante = new RestauranteComNomeSemelhanteEpec(nome);
		return repository.findAll(comFreteGratis.and(comNomeSemelhante));
	}
}
