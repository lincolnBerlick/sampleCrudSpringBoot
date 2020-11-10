package com.amostra.crud.Usuario.service;

import com.amostra.crud.config.comum.exceptions.ValidacaoException;
import com.amostra.crud.modules.usuario.model.Usuario;
import com.amostra.crud.modules.usuario.service.UsuarioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static com.amostra.crud.Usuario.helpers.usuarios.umUsuarioRequestPadrao;
import static com.amostra.crud.Usuario.helpers.usuarios.umUsuarioRequestUpdateDto;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:/usuario.sql"})
@Transactional
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

        var usuarioParaAtualizar = umUsuarioRequestUpdateDto(11, "43491891060");
        usuarioParaAtualizar.setNome("Ciclano");

        assertThat(usuarioService.updateUsuario(usuarioParaAtualizar))
                .extracting(Usuario::getId, Usuario::getNome, Usuario::getCpf, Usuario::getDataNascimento)
                .contains(11, "Ciclano", "43491891060", LocalDate.of(1991, 3, 7));
    }

    @Test(expected = ValidacaoException.class)
    public void exceptionUpdate_deveLancarException_quandoUpdateComUsuarioInexistente() {
        usuarioService.updateUsuario(umUsuarioRequestUpdateDto(11020, "95930174024"));
    }

    @Test
    public void validacpf_deveSalvarCpfSemCaracteresEspeciais_quandoSave()  {
        var usuarioParaAtualizar = umUsuarioRequestUpdateDto(11, "315.921.000-66");
        usuarioParaAtualizar.setNome("Ciclano");

        assertThat(usuarioService.updateUsuario(usuarioParaAtualizar))
                .extracting(Usuario::getId, Usuario::getNome, Usuario::getCpf, Usuario::getDataNascimento)
                .contains(11, "Ciclano", "31592100066", LocalDate.of(1991, 3, 7));
    }
}
