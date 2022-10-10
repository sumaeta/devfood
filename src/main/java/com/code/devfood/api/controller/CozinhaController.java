package com.code.devfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.devfood.domain.model.Cozinha;
import com.code.devfood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping(value = "/cozinhas", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CozinhaController {
	
	private final CozinhaRepository repository;
	
	@Autowired
	public CozinhaController(CozinhaRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<List<Cozinha>> listar(){
		List<Cozinha> cozinhas = repository.findAll();
		return ResponseEntity.ok(cozinhas);
	}
}
