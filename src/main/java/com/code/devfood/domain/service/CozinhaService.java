package com.code.devfood.domain.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.devfood.domain.model.Cozinha;
import com.code.devfood.domain.repository.CozinhaRepository;

/**
 * @author breno
 *
 */
@Service
public class CozinhaService {

	private final CozinhaRepository repository;

	@Autowired
	public CozinhaService(CozinhaRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		Cozinha obj = this.repository.save(cozinha);
		return obj;
	}

	@Transactional(readOnly = true)
	public Cozinha buscar(Long id) {
		Optional<Cozinha> obj = this.repository.findById(id);

		if (obj.isEmpty()) {
			throw new EntityNotFoundException("Cozinha Não Encontrada");
		}

		return obj.get();
	}
	
	@Transactional(readOnly = true)
	public List<Cozinha> listar(){
		return this.repository.findAll();
	}

	@Transactional
	public void remover(Long id) {
		try {
			this.buscar(id);
			this.repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			e.getMessage();
		}
	}
	
	@Transactional
	public void atualizar(Long id, Cozinha cozinha) {
		try {
			Cozinha obj = this.buscar(id);
			obj.setNome(cozinha.getNome());
			this.salvar(obj);
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
