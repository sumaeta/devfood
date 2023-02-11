package com.code.devfood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.devfood.domain.exception.EntidadeEmUsoException;
import com.code.devfood.domain.exception.EntidadeNaoEncontradaException;
import com.code.devfood.domain.model.Cidade;
import com.code.devfood.domain.model.Estado;
import com.code.devfood.domain.repository.CidadeRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CidadeService {

	public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser excluida, pois esta em uso";
	private final CidadeRepository repository;
	private final EstadoService service;

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
		try {
			this.repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, id));
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException("Cidade Não Encontrada");
		}
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
		Long estadoId = obj.getEstado().getId();
		Estado estado = this.service.buscar(estadoId);

		if (estado == null)
			throw new EntidadeNaoEncontradaException(String.format("Estado com Id: %d não foi encontrado", estadoId));

		obj.setEstado(estado);
		return this.repository.save(obj);
	}
}
