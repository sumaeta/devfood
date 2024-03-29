package com.code.devfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.devfood.domain.model.Cozinha;
import com.code.devfood.domain.service.CozinhaService;

@RestController
@RequestMapping(value = "/cozinhas", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class CozinhaController {

	private final CozinhaService service;

	@Autowired
	public CozinhaController(CozinhaService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Cozinha>> listar() {
		List<Cozinha> cozinhas = this.service.listar();
		return ResponseEntity.ok(cozinhas);
	}

	@GetMapping(value = "/{id}")
	public Cozinha buscar(@PathVariable Long id) {
		return this.service.buscar(id);
	}

	@PostMapping
	public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha) {
		this.service.salvar(cozinha);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(value = "/{id}")
	public Cozinha atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
		return this.service.atualizar(id, cozinha);
	}

	@DeleteMapping(value = "/{id}")
	public void remover(@PathVariable Long id) {
		this.service.remover(id);
	}
	
	@GetMapping(value = "/por-nome")
	public ResponseEntity<List<Cozinha>> buscarPorNome(String nome){
		List<Cozinha> retorno = this.service.buscarPorNome(nome);
		return ResponseEntity.ok(retorno);
	}
}
