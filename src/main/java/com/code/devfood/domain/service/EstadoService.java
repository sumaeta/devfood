package com.code.devfood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.devfood.domain.exception.EntidadeEmUsoException;
import com.code.devfood.domain.exception.EntidadeNaoEncontradaException;
import com.code.devfood.domain.model.Estado;
import com.code.devfood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	private final EstadoRepository repository;

	@Autowired
	public EstadoService(EstadoRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public List<Estado> listar() {
		return this.repository.findAll();
	}

	@Transactional
	public Estado buscar(Long id) {
		return this.repository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Estado com id: %d não foi encontrado", id)));
	}

	@Transactional
	public void remover(Long id) {
		try {
			this.repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser excluida, pois esta em uso", id));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Estado Não Encontrada");
		}
	}

	@Transactional
	public Estado atualizar(Long id, Estado estado) {
		Estado obj = this.buscar(id);
		BeanUtils.copyProperties(estado, obj, "id");
		this.salvar(obj);
		return obj;
	}

	@Transactional
	public Estado salvar(Estado obj) {
		return this.repository.save(obj);
	}
}
