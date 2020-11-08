package com.amostra.crud.modules.contato.repository;

import com.amostra.crud.modules.contato.model.Contato;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContatoRepository extends PagingAndSortingRepository<Contato, Integer>,
        QuerydslPredicateExecutor<Contato>, ContatoRepositoryCustom {
}
