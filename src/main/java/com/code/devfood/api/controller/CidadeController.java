package com.code.devfood.api.controller;

import java.util.List;

import com.code.devfood.domain.exception.EntidadeEmUsoException;
import com.code.devfood.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.code.devfood.domain.model.Cidade;
import com.code.devfood.domain.service.CidadeService;
import org.springframework.web.server.ResponseStatusException;

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
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		try {
			this.service.remover(id);
		}catch (EntidadeNaoEncontradaException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Cidade de código %d não pode ser excluida, pois esta em uso", id));
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Cidade> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
		Cidade retorno = this.service.atualizar(id, cidade);
		return ResponseEntity.ok().body(retorno);
	}
}
