package com.example.nupsisign.Model.Dbset;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@Builder
@Table(name = "tb_arquivo")
public class Arquivos {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idArquivo;

    @Getter
    @JsonFormat(pattern="HH:mm | dd/MM/yyyy", timezone="GMT-3")
    private Date uploadDate = new Date();

    @Getter
    private String certidãoDeNascimento;

    @Getter
    private String identidade;

    @Getter
    private String certidãoDeCasamento;

    @Getter
    private String escrituraDeDivorcio;

    @Getter
    private String cpf;

    @Getter
    private String endereço;


    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Arquivos() {

    }
}
