package com.code.devfood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
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

	private static final String MSG_COZINHA_NÃO_ENCONTRADA = "Cozinha Não Encontrada";
	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser excluida, pois esta em uso";
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
		return obj.orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_COZINHA_NÃO_ENCONTRADA));
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
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, id));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(MSG_COZINHA_NÃO_ENCONTRADA);
		}
	}
	
	@Transactional
	public Cozinha atualizar(Long id, Cozinha cozinha) {
		Cozinha obj = this.buscar(id);
		BeanUtils.copyProperties(cozinha, obj, "id");
		return this.salvar(obj);
	}

	@Transactional
	public List<Cozinha> buscarPorNome(String nome){
		return this.repository.findByNomeContaining(nome);
	}
}
