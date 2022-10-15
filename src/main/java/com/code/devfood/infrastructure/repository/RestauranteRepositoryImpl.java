package com.code.devfood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.code.devfood.domain.model.Restaurante;
import com.code.devfood.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

		var jpql = new StringBuilder();

		jpql.append("from Restaurante where 0 = 0 ");
		
		var parametros = new HashMap<String, Object>();

		if (StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}

		if (taxaInicial != null) {
			jpql.append("and taxaFrete >= :taxaInicial ");
			parametros.put("taxaInicial", taxaInicial);
		}

		if (taxaFinal != null) {
			jpql.append("and taxaFrete <= :taxaFinal ");
			parametros.put("taxaFinal", taxaFinal);
		}
		
		 TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
		 
		 parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
		 
		 return query.getResultList();
	}

	public List<Restaurante> busca(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		
		Root<Restaurante> root =  criteria.from(Restaurante.class);
		Predicate predicateNome = builder.like(root.get("nome"), "%" + nome + "%");
		
		Predicate predicateTaxaInicial = builder
				.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial);
		
		Predicate predicateTaxaFinal = builder
				.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal);
		
		criteria.where(predicateNome, predicateTaxaInicial, predicateTaxaFinal);
		
		return manager.createQuery(criteria).getResultList();
	}
}
