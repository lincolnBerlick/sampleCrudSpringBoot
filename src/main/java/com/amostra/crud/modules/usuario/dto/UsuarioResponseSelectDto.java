package com.amostra.crud.modules.usuario.dto;

import com.amostra.crud.modules.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponseSelectDto {

    private Integer usuarioId;
    private String nome;

    public static UsuarioResponseSelectDto of(Usuario usuario) {
       return UsuarioResponseSelectDto.builder()
                .usuarioId(usuario.getId())
                .nome(usuario.getNome())
                .build();
    }
}
