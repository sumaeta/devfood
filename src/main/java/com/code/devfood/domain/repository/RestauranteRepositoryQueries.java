package com.code.devfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.code.devfood.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
}
