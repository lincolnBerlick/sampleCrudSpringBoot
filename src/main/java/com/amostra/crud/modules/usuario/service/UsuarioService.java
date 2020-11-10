package com.amostra.crud.modules.usuario.service;

import com.amostra.crud.config.comum.exceptions.ValidacaoException;
import com.amostra.crud.modules.usuario.dto.UsuarioRequest;
import com.amostra.crud.modules.usuario.dto.UsuarioRequestUpdateDto;
import com.amostra.crud.modules.usuario.dto.UsuarioResponseSelectDto;
import com.amostra.crud.modules.usuario.filtros.UsuarioRequestFiltro;
import com.amostra.crud.modules.usuario.model.Usuario;
import com.amostra.crud.modules.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario save(UsuarioRequest usuarioRequest) {
        validarSave(usuarioRequest);
       return usuarioRepository.save(Usuario.of(usuarioRequest));
    }

    public Usuario getById(Integer usuarioId) {
        return usuarioRepository.findById(usuarioId).get();
    }

    public void deleteById(Integer usuarioId) {
        findOneOrElseThrow(usuarioId);
        usuarioRepository.deleteById(usuarioId);
    }

    public Usuario updateUsuario(UsuarioRequestUpdateDto usuarioRequestUpdateDto) {
        validarUpdate(usuarioRequestUpdateDto);
        return usuarioRepository.save(Usuario.of(usuarioRequestUpdateDto));
    }

    private Optional<Usuario> findOneOrElseThrow(Integer usuarioId) {
       return Optional.ofNullable(usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ValidacaoException("Usuário não encontrado")));
    }

    public Page<Usuario> findAll(Pageable pageable, UsuarioRequestFiltro filtro) {
        validarFiltros(filtro);
        return usuarioRepository.findAll(filtro.toPredicate(), pageable);
    }

    public List<UsuarioResponseSelectDto> getSelectUsers() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponseSelectDto::of)
                .collect(Collectors.toList());
    }

    private void validarSave(UsuarioRequest usuarioRequest) {
        usuarioRequest.setCpf(tratarCpf(usuarioRequest.getCpf()));

    }

    private void validarUpdate(UsuarioRequestUpdateDto usuarioRequestUpdateDto) {
        findOneOrElseThrow(usuarioRequestUpdateDto.getId());
        usuarioRequestUpdateDto.setCpf(tratarCpf(usuarioRequestUpdateDto.getCpf()));
    }

    private void validarFiltros(UsuarioRequestFiltro usuarioRequestFiltro) {
        usuarioRequestFiltro.setCpf(tratarCpf(usuarioRequestFiltro.getCpf()));
    }

    public String tratarCpf(String cpf) {
        return !ObjectUtils.isEmpty(cpf) ?cpf.replaceAll("[^a-zA-Z0-9]", "") : null;
    }
}
