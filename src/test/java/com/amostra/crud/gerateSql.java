package com.amostra.crud;

import com.amostra.crud.modules.usuario.repository.UsuarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class gerateSql {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void criarssTest() {

        usuarioRepository.findAll();
    }

}
