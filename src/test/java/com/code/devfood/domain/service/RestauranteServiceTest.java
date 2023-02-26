package com.code.devfood.domain.service;

import com.code.devfood.domain.exception.EntidadeNaoEncontradaException;
import com.code.devfood.domain.model.Restaurante;
import com.code.devfood.domain.repository.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RestauranteServiceTest {

    @Mock
    private RestauranteRepository repository;

    @InjectMocks
    private RestauranteService service;

    @Test
    void testListar() {
        MockitoAnnotations.openMocks(this);

        List<Restaurante> restaurantesRetornados = new ArrayList<>();

        when(repository.findAll()).thenReturn(restaurantesRetornados);

        List<Restaurante> restaurantesEsperados = service.listar();

        assertIterableEquals(restaurantesRetornados, restaurantesEsperados);
    }

    @Test
    void testBuscarRestauranteExistente() {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        when(repository.findById(restaurante.getId())).thenReturn(Optional.of(restaurante));

        Restaurante resultado = service.buscar(restaurante.getId());

        assertEquals(restaurante, resultado);
    }

    @Test
    void testBuscarRestauranteInexistente() {
        Long idInexistente = 2L;
        when(repository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.buscar(idInexistente);
        });
    }
}