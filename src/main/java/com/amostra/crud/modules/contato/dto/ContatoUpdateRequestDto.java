package com.amostra.crud.modules.contato.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContatoUpdateRequestDto {

    @NotNull
    Integer contatoId;

    @NotEmpty
    private String nome;

    @Email
    private String email;

    @NotEmpty
    @NumberFormat
    private String telefone;
}
