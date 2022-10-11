package com.code.devfood.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.devfood.domain.model.Cidade;
import com.code.devfood.domain.service.CidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	private final CidadeService service;

	public CidadeController(CidadeService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Cidade>> listar() {
		List<Cidade> cidades = service.listar();
		return ResponseEntity.ok(cidades);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long id) {
		Cidade retorno = this.service.buscar(id);
		return ResponseEntity.ok(retorno);
	}

	@PostMapping
	public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade) {
		Cidade retorno = this.service.salvar(cidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Cidade> remover(@PathVariable Long id) {
		this.service.remover(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Cidade> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
		Cidade retorno = this.service.atualizar(id, cidade);
		return ResponseEntity.ok().body(retorno);
	}
}
