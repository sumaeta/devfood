package com.code.devfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.code.devfood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, 
												RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante>{

	@Query("from Restaurante r join fetch r.cozinha")
	List<Restaurante> findAll();
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	List<Restaurante> consultarPorNome(@Param("id") Long id, String nome);
	List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);
}
