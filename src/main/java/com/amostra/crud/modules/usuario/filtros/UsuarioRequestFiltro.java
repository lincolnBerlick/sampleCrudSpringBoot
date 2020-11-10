package com.amostra.crud.modules.usuario.filtros;

import com.amostra.crud.modules.usuario.predicate.UsuarioPredicate;
import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestFiltro {

    private Integer id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    public BooleanBuilder toPredicate() {
        return new UsuarioPredicate()
                .comId(id)
                .comData(dataNascimento)
                .comNome(nome)
                .comCpf(cpf)
                .build();
    }
}
