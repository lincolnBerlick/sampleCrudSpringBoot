package com.amostra.crud.modules.contato.model;

import com.amostra.crud.modules.contato.dto.ContatoSaveRequestDto;
import com.amostra.crud.modules.contato.dto.ContatoUpdateRequestDto;
import com.amostra.crud.modules.usuario.model.Usuario;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CONTATO")
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class Contato {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_CONTATO", sequenceName = "SEQ_CONTATO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CONTATO")
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "TELEFONE", unique = true)
    private String telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USUARIO", referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_CONTATO_USUARIO"))
    private Usuario usuario;

    public static Contato of(ContatoSaveRequestDto contatoSaveRequestDto) {
       return Contato.builder()
                .nome(contatoSaveRequestDto.getNome())
                .email(contatoSaveRequestDto.getEmail())
                .telefone(contatoSaveRequestDto.getTelefone())
                .usuario(Usuario.builder()
                        .id(contatoSaveRequestDto.getUsuarioId())
                        .build())
                .build();
    }

    public static Contato of(ContatoUpdateRequestDto contatoUpdateRequestDto) {
        return Contato.builder()
                .id(contatoUpdateRequestDto.getContatoId())
                .nome(contatoUpdateRequestDto.getNome())
                .email(contatoUpdateRequestDto.getEmail())
                .telefone(contatoUpdateRequestDto.getTelefone())
                .usuario(Usuario.builder()
                        .id(contatoUpdateRequestDto.getUsuarioId())
                        .build())
                .build();
    }

}
