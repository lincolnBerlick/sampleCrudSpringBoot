package com.amostra.crud.modules.usuario.model;

import com.amostra.crud.modules.contato.model.Contato;
import com.amostra.crud.modules.usuario.dto.UsuarioRequest;
import com.amostra.crud.modules.usuario.dto.UsuarioRequestUpdateDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_USER")
    private Integer id;

    @NotEmpty
    @Column(name = "NOME")
    String nome;


    @Column(name = "CPF", unique = true)
    @CPF
    @NotEmpty
    String cpf;

    @Column(name = "DATA_NASCIMENTO")
    @Past
    @NotNull
    private LocalDate dataNascimento;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Contato> contatos;


    public static Usuario of(UsuarioRequest usuarioRequest) {
        return Usuario.builder()
                .nome(usuarioRequest.getNome())
                .cpf(usuarioRequest.getCpf())
                .dataNascimento(usuarioRequest.getDataNascimento())
                .build();
    }

    public static Usuario of(UsuarioRequestUpdateDto usuarioRequestUpdateDto) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioRequestUpdateDto, usuario);
        return usuario;
    }
}
