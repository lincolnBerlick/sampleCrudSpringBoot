package com.amostra.crud.modules.contato.controller;


import com.amostra.crud.modules.contato.dto.ContatoSaveRequestDto;
import com.amostra.crud.modules.contato.dto.ContatoUpdateRequestDto;
import com.amostra.crud.modules.contato.filtros.ContatoFiltros;
import com.amostra.crud.modules.contato.model.Contato;
import com.amostra.crud.modules.contato.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/contato")
public class ContatoController {

    @Autowired
    ContatoService contatoService;

    @PostMapping
    public Contato saveContato(@RequestBody @Valid ContatoSaveRequestDto contatoSaveRequestDto) {
        return contatoService.save(contatoSaveRequestDto);
    }

    @GetMapping
    public Contato getById(@RequestParam Integer contatoId) {
        return contatoService.getById(contatoId);
    }

    @DeleteMapping("/delete/{contatoId}")
    public void deleteById(@PathVariable Integer contatoId) {
        contatoService.deleteById(contatoId);
    }

    @PutMapping
    public Contato updateContato(@Valid @RequestBody ContatoUpdateRequestDto contatoUpdateRequestDto) {
        return contatoService.update(contatoUpdateRequestDto);
    }

    @GetMapping("/findAll")
    public Page<Contato> findAll(Pageable pageable, ContatoFiltros contatoFiltros) {
        return contatoService.findAll(pageable, contatoFiltros);
    }
}
