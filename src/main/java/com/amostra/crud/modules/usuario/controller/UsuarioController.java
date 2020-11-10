package com.amostra.crud.modules.usuario.controller;

import com.amostra.crud.modules.usuario.dto.UsuarioRequest;
import com.amostra.crud.modules.usuario.dto.UsuarioRequestUpdateDto;
import com.amostra.crud.modules.usuario.dto.UsuarioResponseSelectDto;
import com.amostra.crud.modules.usuario.filtros.UsuarioRequestFiltro;
import com.amostra.crud.modules.usuario.model.Usuario;
import com.amostra.crud.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public Usuario save(@RequestBody @Validated UsuarioRequest usuario) {
        return service.save(usuario);
    }

    @GetMapping
    public Usuario getById(@RequestParam Integer usuarioId) {
        return service.getById(usuarioId);
    }

    @DeleteMapping("/delete/{usuarioId}")
    public void deleteUsuarioById(@PathVariable Integer usuarioId) {
        service.deleteById(usuarioId);

    }

    @PutMapping
    public Usuario updateUsuario(@RequestBody @Valid  UsuarioRequestUpdateDto usuarioRequestUpdateDto) {
        return service.updateUsuario(usuarioRequestUpdateDto);
    }

    @GetMapping("/findAll")
    public Page<Usuario> findAll(Pageable pageable, UsuarioRequestFiltro filtro) {
        return service.findAll(pageable, filtro);
    }

    @GetMapping("/findAllSelect")
    public List<UsuarioResponseSelectDto> findAllUsersSelect() {
        return service.getSelectUsers();
    }

}
