package com.amostra.crud.modules.usuario.repository;

import com.amostra.crud.modules.usuario.model.Usuario;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioRepositoryPredicateCustom {

    Page<Usuario> findAll(Predicate predicate, Pageable pageable);
}
