package com.code.devfood.model.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.code.devfood.domain.exception.EntidadeNaoEncontradaException;
import com.code.devfood.domain.model.Restaurante;
import com.code.devfood.domain.repository.RestauranteRepository;
import com.code.devfood.domain.service.CozinhaService;
import com.code.devfood.domain.service.RestauranteService;

public class RestauranteServiceTest {

	@Mock
	private RestauranteRepository repository;

	@Mock
	private CozinhaService cozinhaService;

	@Mock
	private RestauranteService service;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.service = new RestauranteService(repository, cozinhaService);
	}

	@Test
	@DisplayName("Teste de busca de todos os Restaurantes")
	public void deveRetornarListaRestaurante() {
		List<Restaurante> retorno = service.listar();
		assertNotNull(retorno);
	}

	@Test
	@DisplayName("Teste de busca de todos os Restaurantes")
	public void deveRetornarUmaException() {
		try {
			service.buscar(1L);
		} catch (EntidadeNaoEncontradaException e) {
		}
	}
}
