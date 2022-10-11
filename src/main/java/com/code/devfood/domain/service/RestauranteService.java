package com.code.devfood.domain.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import com.code.devfood.domain.exception.EntidadeNaoEncontradaException;
import com.code.devfood.domain.model.Cozinha;
import com.code.devfood.domain.model.Restaurante;
import com.code.devfood.domain.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestauranteService {

	private final RestauranteRepository repository;
	private final CozinhaService service;

	public RestauranteService(RestauranteRepository repository, CozinhaService service) {
		this.repository = repository;
		this.service = service;
	}

	@Transactional
	public List<Restaurante> listar() {
		return this.repository.findAll();
	}

	@Transactional
	public Restaurante buscar(Long id) {
		Optional<Restaurante> obj = this.repository.findById(id);
		return obj.orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Restaurante com o código %d não foi encontrado", id)));
	}

	@Transactional
	public void salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = this.service.buscar(cozinhaId);

		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe o cadastro de cozinha com o código: %d", cozinhaId));
		}

		restaurante.setCozinha(cozinha);
		this.repository.save(restaurante);
	}

	@Transactional
	public Restaurante atualizar(Long id, Restaurante restaurante) {
		Restaurante obj = this.buscar(id);
		BeanUtils.copyProperties(restaurante, obj, "id");
		this.salvar(obj);
		return obj;
	}

	@Transactional
	public Restaurante atualizarParcialmente(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);

		camposOrigem.forEach((nomepropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomepropriedade);
			field.setAccessible(true);

			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			ReflectionUtils.setField(field, restauranteDestino, novoValor);

		});
		this.atualizar(restauranteDestino.getId(), restauranteDestino);
		return restauranteDestino;
	}
}
