package com.amostra.crud.modules.contato.repository;

import com.amostra.crud.modules.contato.model.Contato;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContatoRepositoryCustom {
    Page<Contato> findAll(Predicate predicate, Pageable pageable);
}
