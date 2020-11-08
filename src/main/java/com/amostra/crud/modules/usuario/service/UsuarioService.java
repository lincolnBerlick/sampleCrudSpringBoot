package com.amostra.crud.modules.usuario.service;

import com.amostra.crud.config.comum.exceptions.ValidacaoException;
import com.amostra.crud.modules.usuario.dto.UsuarioRequest;
import com.amostra.crud.modules.usuario.dto.UsuarioRequestUpdateDto;
import com.amostra.crud.modules.usuario.filtros.UsuarioRequestFiltro;
import com.amostra.crud.modules.usuario.model.Usuario;
import com.amostra.crud.modules.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario save(UsuarioRequest usuarioRequest) {
       return usuarioRepository.save(Usuario.of(usuarioRequest));
    }

    public Usuario getById(Integer usuarioId) {
        return usuarioRepository.findById(usuarioId).get();
    }

    public void deleteById(Integer usuarioId) {
        findUserOrThrow(usuarioId);
        usuarioRepository.deleteById(usuarioId);
    }

    public Usuario updateUsuario(UsuarioRequestUpdateDto usuarioRequestUpdateDto) {
        var usuario = Usuario.of(usuarioRequestUpdateDto);
        usuario.setId(usuarioRequestUpdateDto.getUsuarioId());
        findUserOrThrow(usuario.getId());
        return usuarioRepository.save(usuario);
    }

    private void findUserOrThrow(Integer usuarioId) {
        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ValidacaoException("Usuário não encontrado"));
    }

    public Page<Usuario> findAll(Pageable pageable, UsuarioRequestFiltro filtro) {
        return usuarioRepository.findAll(filtro.toPredicate(), pageable);
    }
}
