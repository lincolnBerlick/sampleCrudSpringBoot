package com.amostra.crud.modules.contato.filtros;

import com.amostra.crud.modules.contato.predicate.ContatoPredicate;
import com.querydsl.core.BooleanBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContatoFiltros {

    private Integer usuarioId;
    private Integer id;
    private String nome;
    private String email;
    private String telefone;

    public BooleanBuilder toPredicate() {
        return new ContatoPredicate()
                .comId(id)
                .comNome(nome)
                .comEmail(email)
                .comTelefone(telefone)
                .comUsuarioId(usuarioId)
                .build();
    }
}
