package com.amostra.crud.Usuario.helpers;

import com.amostra.crud.modules.usuario.dto.UsuarioRequest;
import com.amostra.crud.modules.usuario.dto.UsuarioRequestUpdateDto;
import com.amostra.crud.modules.usuario.model.Usuario;

import java.time.LocalDate;
import java.util.Optional;

public class usuarios {

    public static UsuarioRequestUpdateDto umUsuarioRequestUpdateDto(Integer id) {
        return com.amostra.crud.modules.usuario.dto.UsuarioRequestUpdateDto.builder()
                .usuarioId(id)
                .nome("Fulano")
                .cpf("79592718016")
                .dataNascimento(LocalDate.of(1991, 3, 7))
                .build();
    }

    public static UsuarioRequest umUsuarioRequestPadrao(String cpf) {
        return UsuarioRequest.builder()
                .nome("Usuario")
                .cpf(cpf)
                .dataNascimento(LocalDate.of(1991, 3, 7))
                .build();
    }

    public static Optional<Usuario> umUsuarioPadrao() {
        return Optional.ofNullable(Usuario.builder()
                .id(1)
                .nome("Usuario")
                .cpf("81874571040")
                .dataNascimento(LocalDate.of(1991, 3, 7))
                .build());
    }
}
