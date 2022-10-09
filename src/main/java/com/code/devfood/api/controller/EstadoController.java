package com.code.devfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.devfood.domain.model.Estado;
import com.code.devfood.domain.repository.EstadoRepository;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

	private final EstadoRepository repository;
	
	@Autowired
	public EstadoController(EstadoRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public ResponseEntity<List<Estado>> listar(){
		List<Estado> estados = repository.findAll();
		return ResponseEntity.ok(estados);
	}
}
