package com.code.devfood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.devfood.domain.exception.EntidadeNaoEncontradaException;
import com.code.devfood.domain.model.Cozinha;
import com.code.devfood.domain.model.Restaurante;
import com.code.devfood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	private final RestauranteRepository repository;
	private final CozinhaService service;
	
	@Autowired
	public RestauranteService(RestauranteRepository repository, CozinhaService service) {
		this.repository = repository;
		this.service = service;
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

	@Transactional
	public void salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = this.service.buscar(cozinhaId);
		
		if (cozinha == null ) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe o cadastro de cozinha com o código: %d", cozinhaId));
		}
		
		restaurante.setCozinha(cozinha);
		this.repository.save(restaurante);
	}
}
