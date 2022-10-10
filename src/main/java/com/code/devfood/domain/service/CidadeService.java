package com.code.devfood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.devfood.domain.exception.EntidadeNaoEncontradaException;
import com.code.devfood.domain.model.Cidade;
import com.code.devfood.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	private final CidadeRepository repository;
	private final EstadoService service;
	
	@Autowired
	public CidadeService(CidadeRepository repository, EstadoService service) {
		this.repository = repository;
		this.service = service;
	}

	@Transactional
	public List<Cidade> listar() {
		return this.repository.findAll();
	}

	@Transactional
	public Cidade buscar(Long id) {
		return this.repository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Cidade com id: %d não foi encontrado", id)));
	}

	@Transactional
	public void remover(Long id) {
		this.buscar(id);
		this.repository.deleteById(id);
	}

	@Transactional
	public Cidade atualizar(Long id, Cidade cidade) {
		Cidade obj = this.buscar(id);
		BeanUtils.copyProperties(cidade, obj, "id");
		this.salvar(obj);
		return obj;
	}

	@Transactional
	public Cidade salvar(Cidade obj) {
		return this.repository.save(obj);
	}
}
