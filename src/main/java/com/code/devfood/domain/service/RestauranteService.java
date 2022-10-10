package com.code.devfood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.devfood.domain.exception.EntidadeNaoEncontradaException;
import com.code.devfood.domain.model.Restaurante;
import com.code.devfood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	private final RestauranteRepository repository;
	
	@Autowired
	public RestauranteService(RestauranteRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public List<Restaurante> listar(){
		return this.repository.findAll();
	}
	
	@Transactional
	public Restaurante buscar(Long id) {
		Optional<Restaurante> obj = this.repository.findById(id);
		return obj.orElseThrow(()-> new EntidadeNaoEncontradaException(String.format("Restaurante com o código %d não foi encontrado", id)));
	}
}
