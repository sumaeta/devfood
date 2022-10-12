package com.code.devfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.code.devfood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{

	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	
	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNome(@Param("id") Long id, String nome);
}
