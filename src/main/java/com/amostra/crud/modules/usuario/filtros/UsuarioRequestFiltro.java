package com.amostra.crud.modules.usuario.filtros;

import com.amostra.crud.config.comum.implementa.DateSerializator;
import com.amostra.crud.modules.usuario.predicate.UsuarioPredicate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.querydsl.core.BooleanBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
