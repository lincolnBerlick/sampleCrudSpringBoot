package com.amostra.crud.modules.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequestUpdateDto {

    @NotNull(message = "Usuário inválido")
    private Integer usuarioId;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;
}
