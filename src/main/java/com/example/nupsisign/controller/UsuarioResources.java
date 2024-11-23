package com.example.nupsisign.controller;

import com.example.nupsisign.Model.Dbset.Usuario;
import com.example.nupsisign.Model.Repositorio.UsuarioRepositorio;
import com.example.nupsisign.Model.Util.RestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    // Sava um novo usuário
    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        Usuario saved = usuarioRepositorio.save(usuario);
        if (saved == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = RestUtil.getUri(saved.getIdUsuario());
        return ResponseEntity.created(uri).body(saved);
    }

}
