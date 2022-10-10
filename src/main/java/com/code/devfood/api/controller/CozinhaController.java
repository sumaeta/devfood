package com.code.devfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.devfood.domain.model.Cozinha;
import com.code.devfood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping(value = "/cozinhas", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class CozinhaController {

	private final CozinhaRepository repository;

	@Autowired
	public CozinhaController(CozinhaRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<List<Cozinha>> listar() {
		List<Cozinha> cozinhas = this.repository.findAll();
		return ResponseEntity.ok(cozinhas);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Cozinha cozinha = this.repository.findById(id).get();
		return ResponseEntity.ok(cozinha);
	}

	@PostMapping
	public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha) {
		this.repository.save(cozinha);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
		Cozinha obj = this.buscar(id).getBody();

		if (obj != null) {
			obj.setNome(cozinha.getNome());

			this.salvar(obj);
			return ResponseEntity.ok().build();

		}

		return ResponseEntity.notFound().build();
	}
}
