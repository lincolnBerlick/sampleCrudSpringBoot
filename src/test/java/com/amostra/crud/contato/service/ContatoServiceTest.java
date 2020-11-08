package com.amostra.crud.contato.service;

import com.amostra.crud.config.comum.exceptions.ValidacaoException;
import com.amostra.crud.contato.helpers.ContatoHelper;
import com.amostra.crud.modules.contato.model.Contato;
import com.amostra.crud.modules.contato.service.ContatoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static com.amostra.crud.contato.helpers.ContatoHelper.umContatoRequeset;
import static com.amostra.crud.contato.helpers.ContatoHelper.umContatoUpdateRequest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:/usuario.sql"})
@Sql(scripts = {"classpath:/contato.sql"})
public class ContatoServiceTest {

    @Autowired
    ContatoService contatoService;

    @Test
    public void save_deveSalvarContatoVinculandoAUsario_quandoSaveContato() {
        var contato = umContatoRequeset("email@email.com.br", "33333333", 10);
        assertThat(contatoService.save(contato))
                .extracting(Contato::getNome, Contato::getEmail, Contato::getTelefone)
                .contains("Contato", "email@email.com.br", "33333333");
    }

    @Test
    public void update_deveAtualizarContatoEManterUsuario_quandoUpdate() {
        var contatoUpdate =
                umContatoUpdateRequest(101, "Nome", "emailupdate@email.com.br", "33333");
        assertThat(contatoService.getById(101))
                .extracting(Contato::getNome, Contato::getEmail, Contato::getTelefone)
                .contains("USERCONTATO2","EMAIL2@EMAIL.COM.BR", "33344444");
        assertThat(contatoService.update(contatoUpdate))
                .extracting(Contato::getNome, Contato::getEmail, Contato::getTelefone)
                .contains("Nome", "emailupdate@email.com.br", "33333");
    }

    @Test(expected = ValidacaoException.class)
    public void updateException_deveLancarException_quandoUpdateUsuarioInexistente() {
       var contatoTuUpdateException = umContatoUpdateRequest(105, "Nome", "emailupdate@email.com.br", "33333");
        contatoService.update(contatoTuUpdateException);
    }
}
