package com.amostra.crud.modules.usuario.repository;

import com.amostra.crud.modules.usuario.model.Usuario;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer>,
        QuerydslPredicateExecutor<Usuario>, UsuarioRepositoryPredicateCustom {

    List<Usuario> findAll();
}
