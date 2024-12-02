package com.example.nupsisign.Model.Dbset;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Builder
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idUsuario;

    @Getter
    private String nome;

    private String email;

    @Getter
    private UserRole role;

    @Getter
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_arquivo")
    private Arquivos arquivos;

    public Usuario() {

    }

    public Usuario(String email, String encryptedPassword, UserRole role, String nome) {
        this.email = email;
        this.senha = encryptedPassword;
        this.role = role;
        this.nome = nome;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
