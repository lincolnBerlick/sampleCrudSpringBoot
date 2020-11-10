package com.amostra.crud.contato.helpers;

import com.amostra.crud.modules.contato.dto.ContatoSaveRequestDto;
import com.amostra.crud.modules.contato.dto.ContatoUpdateRequestDto;

public class ContatoHelper {

public static ContatoSaveRequestDto umContatoRequeset(String email, String telefone, Integer usuarioId) {
    return ContatoSaveRequestDto.builder()
            .nome("Contato")
            .email(email)
            .telefone(telefone)
            .usuarioId(usuarioId)
            .build();
    }

    public static ContatoUpdateRequestDto umContatoUpdateRequest(Integer id, String nome, String email, String telefone) {
    return ContatoUpdateRequestDto.builder()
            .contatoId(id)
            .nome(nome)
            .email(email)
            .telefone(telefone)
            .usuarioId(10)
            .build();
    }


}
