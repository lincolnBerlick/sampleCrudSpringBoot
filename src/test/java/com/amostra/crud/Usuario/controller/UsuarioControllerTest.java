package com.amostra.crud.Usuario.controller;

import com.amostra.crud.modules.usuario.service.UsuarioService;
import helpers.Converter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.amostra.crud.Usuario.helpers.usuarios.umUsuarioPadrao;
import static com.amostra.crud.Usuario.helpers.usuarios.umUsuarioRequestPadrao;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    public static String USARIO_URL = "/api/usuario";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    public void error_deveRetornar400_quandoSaveComDataForadoPadrao() throws Exception {
        var umUusarioRequestToSave = umUsuarioRequestPadrao("81874571040");
        when(usuarioService.save(umUusarioRequestToSave)).thenReturn(umUsuarioPadrao().get());

        mvc.perform(post(USARIO_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Converter.asJsonString(umUusarioRequestToSave)))
                .andExpect(status().isBadRequest());
    }
}
