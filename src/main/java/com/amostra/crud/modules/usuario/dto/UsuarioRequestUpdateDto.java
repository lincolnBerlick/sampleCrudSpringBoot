package com.amostra.crud.modules.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequestUpdateDto {

    @NotNull(message = "Usuário inválido")
    private Integer id;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;
}
