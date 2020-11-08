package com.amostra.crud.Usuario.service;

import com.amostra.crud.config.comum.exceptions.ValidacaoException;
import com.amostra.crud.modules.usuario.dto.UsuarioRequest;
import com.amostra.crud.modules.usuario.dto.UsuarioRequestUpdateDto;
import com.amostra.crud.modules.usuario.model.Usuario;
import com.amostra.crud.modules.usuario.service.UsuarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static com.amostra.crud.Usuario.helpers.usuarios.umUsuarioRequestPadrao;
import static com.amostra.crud.Usuario.helpers.usuarios.umUsuarioRequestUpdateDto;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:/usuario.sql"})
public class UsuarioServiceTest {

    @Autowired
    UsuarioService usuarioService;

    @Test
    public void save_deveSalvarUsuario_quandoSolitarSaveNoRepository() {
        assertThat(usuarioService.save(umUsuarioRequestPadrao("25404170000")))
                .extracting(Usuario::getNome, Usuario::getCpf, Usuario::getDataNascimento)
                .contains("Usuario", "25404170000", LocalDate.of(1991, 3, 7));
    }

    @Test
    public void findById_deveRetornarUsuario_quandoReceberIdUsuario() {
        assertThat(usuarioService.getById(10))
                .extracting(Usuario::getId, Usuario::getNome, Usuario::getCpf, Usuario::getDataNascimento)
                .contains(10, "Usuario", "91327277042", LocalDate.of(1991, 3, 7));
    }

    @Test
    public void update_deveAtualizarUsuarioEManterInformacoes_quandoSolicitarAtualizacao() {

        var usuarioParaAtualizar = umUsuarioRequestUpdateDto(11);
        usuarioParaAtualizar.setNome("Ciclano");

        assertThat(usuarioService.updateUsuario(usuarioParaAtualizar))
                .extracting(Usuario::getId, Usuario::getNome, Usuario::getCpf, Usuario::getDataNascimento)
                .contains(11, "Ciclano", "79592718016", LocalDate.of(1991, 3, 7));
    }

    @Test(expected = Exception.class)
    public void exceptionSave_deveLancarException_quandoSolicitarSaveViolandoConstrantCpf() {
        var userToSave = umUsuarioRequestPadrao("91327277042");
        usuarioService.save(userToSave);
    }

    @Test(expected = ValidacaoException.class)
    public void exceptionUpdate_deveLancarException_quandoUpdateComUsuarioInexistente() {
        usuarioService.updateUsuario(umUsuarioRequestUpdateDto(1000));
    }
}
