package com.amostra.crud.modules.contato.predicate;

import com.querydsl.core.BooleanBuilder;
import org.springframework.util.ObjectUtils;

import static com.amostra.crud.modules.contato.model.QContato.contato;

public class ContatoPredicate {

    private BooleanBuilder builder;

    public ContatoPredicate() {
        this.builder = new BooleanBuilder();
    }

    public BooleanBuilder build() {
        return this.builder;
    }

    public ContatoPredicate comNome(String nome) {
        if(!ObjectUtils.isEmpty(nome)) {
            builder.and(contato.nome.likeIgnoreCase(nome));
        }
        return this;
    }
    public ContatoPredicate comId(Integer id) {
        if(id != null) {
            builder.and(contato.id.eq(id));
        }
        return this;
    }

    public ContatoPredicate comEmail(String email) {
        if(!ObjectUtils.isEmpty(email)) {
            builder.and(contato.email.equalsIgnoreCase(email));
        }
        return this;
    }

    public ContatoPredicate comTelefone(String telefone) {
        if(!ObjectUtils.isEmpty(telefone)) {
            builder.and(contato.telefone.eq(telefone));
        }
        return this;
    }

    public ContatoPredicate comUsuarioId(Integer usuarioId) {
        if(usuarioId != null) {
            builder.and(contato.usuario.id.eq(usuarioId));
        }
        return this;
    }
}
