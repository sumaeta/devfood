package com.code.devfood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		this.buscar(id);
		this.repository.deleteById(id);
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
