package com.example.nupsisign.controller;


import com.example.nupsisign.Model.Dbset.Usuario;
import com.example.nupsisign.Model.Repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
public class UsuarioResources {

    // Injeção de dependência
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    // Retorna todos os usuários cadastrados
    @GetMapping
    public ResponseEntity<List<Usuario>> get() {
        List<Usuario> clienteList = usuarioRepositorio.findAll();
        if (clienteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteList);
    }

}
