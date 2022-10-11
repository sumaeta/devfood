package com.code.devfood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.devfood.domain.exception.EntidadeEmUsoException;
import com.code.devfood.domain.exception.EntidadeNaoEncontradaException;
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

	//@Transactional(readOnly = true)
	public Cozinha buscar(Long id) {
		Optional<Cozinha> obj = this.repository.findById(id);
		return obj.orElseThrow(() -> new EntidadeNaoEncontradaException("Cozinha Não Encontrada"));
	}
	
	@Transactional(readOnly = true)
	public List<Cozinha> listar(){
		return this.repository.findAll();
	}

	@Transactional
	public void remover(Long id) {
		try {
			this.repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cozinha de código %d não pode ser excluida, pois esta em uso", id));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Cozinha Não Encontrada");
		}
	}
	
	@Transactional
	public void atualizar(Long id, Cozinha cozinha) {
		try {
			Cozinha obj = this.buscar(id);
			BeanUtils.copyProperties(cozinha, obj, "id");
			this.salvar(obj);
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
