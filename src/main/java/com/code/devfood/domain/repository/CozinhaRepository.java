package com.code.devfood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.code.devfood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long>{

	List<Cozinha> findByNomeContaining(String nome);
}
