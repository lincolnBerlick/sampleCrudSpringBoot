package com.amostra.crud.modules.contato.service;

import com.amostra.crud.config.comum.exceptions.ValidacaoException;
import com.amostra.crud.config.comum.implementa.BeanUtil;
import com.amostra.crud.modules.contato.dto.ContatoSaveRequestDto;
import com.amostra.crud.modules.contato.dto.ContatoUpdateRequestDto;
import com.amostra.crud.modules.contato.filtros.ContatoFiltros;
import com.amostra.crud.modules.contato.model.Contato;
import com.amostra.crud.modules.contato.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    ContatoRepository contatoRepository;

    public Contato save(ContatoSaveRequestDto contatoSaveRequestDto) {
        return contatoRepository.save(Contato.of(contatoSaveRequestDto));
    }

    public Contato getById(Integer contatoId) {
        return contatoRepository.findById(contatoId).orElse(null);
    }

    public void deleteById(Integer contatoId) {
        findOneOrElseThrow(contatoId)
                .ifPresent(contato -> contatoRepository.delete(contato));
    }

    public Contato update(ContatoUpdateRequestDto contatoUpdateRequestDto) {
        var contatoToUpdate = new Contato();
        findOneOrElseThrow(contatoUpdateRequestDto.getContatoId())
                .ifPresent(contato -> {
                    Contato.of(contato, contatoUpdateRequestDto);
                    BeanUtil.copyProperties(contato, contatoToUpdate);
                });
        return contatoRepository.save(contatoToUpdate);
    }

    private Optional<Contato> findOneOrElseThrow(Integer contatoId) {
        return Optional.ofNullable(contatoRepository.findById(contatoId)
                .orElseThrow(() -> new ValidacaoException("Contato não encontrado.")));
    }

    public Page<Contato> findAll(Pageable pageable, ContatoFiltros contatoFiltros) {
       return contatoRepository.findAll(contatoFiltros.toPredicate(), pageable);
    }
}
