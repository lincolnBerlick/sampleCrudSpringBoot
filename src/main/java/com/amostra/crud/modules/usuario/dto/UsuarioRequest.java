package com.amostra.crud.modules.usuario.dto;

import com.amostra.crud.config.comum.implementa.DateSerializator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.apache.tomcat.jni.Local;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioRequest {

    @NotEmpty(message = "Campo nome é Obrigatório")
    private String nome;

    @NotEmpty(message = "Campo cpf é Obrigatório")
    @CPF
    private String cpf;

    @NotNull(message = "Campo data de nascimento é Obrigatório")
    private LocalDate dataNascimento;

}
