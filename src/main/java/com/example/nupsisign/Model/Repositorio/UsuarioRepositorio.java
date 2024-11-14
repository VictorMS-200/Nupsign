package com.example.nupsisign.Model.Repositorio;

import com.example.nupsisign.Model.Dbset.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    UserDetails findByEmail(String email);
}
