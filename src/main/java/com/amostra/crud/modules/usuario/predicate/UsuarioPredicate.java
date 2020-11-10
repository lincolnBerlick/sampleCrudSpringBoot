package com.amostra.crud.modules.usuario.predicate;

import static com.amostra.crud.modules.usuario.model.QUsuario.usuario;
import com.querydsl.core.BooleanBuilder;

import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

public class UsuarioPredicate {

    private BooleanBuilder builder;

    public UsuarioPredicate() {
        this.builder = new BooleanBuilder();
    }

    public BooleanBuilder build() {
        return this.builder;
    }

    public UsuarioPredicate comNome(String nome) {
        if(!ObjectUtils.isEmpty(nome)) {
           builder.and(usuario.nome.containsIgnoreCase(nome));
        }
        return this;
    }

    public UsuarioPredicate comId(Integer id) {
        if(id != null) {
            builder.and(usuario.id.eq(id));
        }
        return this;
    }

    public UsuarioPredicate comData(LocalDate localDate) {
        if(localDate != null) {
            builder.and(usuario.dataNascimento.eq(localDate));
        }
        return this;
    }

    public UsuarioPredicate comCpf(String cpf) {
        if(!ObjectUtils.isEmpty(cpf)) {
            builder.and(usuario.cpf.eq(cpf));
        }
        return this;
    }
}
