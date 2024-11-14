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
    private String IdUsuario;

    @Getter
    private String nome;

    private String email;

    public Usuario() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
