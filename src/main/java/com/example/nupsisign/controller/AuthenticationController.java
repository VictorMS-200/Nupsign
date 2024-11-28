package com.example.nupsisign.controller;

import com.example.nupsisign.Infra.security.TokenService;
import com.example.nupsisign.Model.Authentication.AuthenticationDTO;
import com.example.nupsisign.Model.Authentication.LoginResponseDTO;
import com.example.nupsisign.Model.Authentication.RegisterDTO;
import com.example.nupsisign.Model.Dbset.UserRole;
import com.example.nupsisign.Model.Dbset.Usuario;
import com.example.nupsisign.Model.Repositorio.UsuarioRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());

        var auth = this.authenticationManager.authenticate(userNamePassword);

        if (auth == null) {
            return ResponseEntity.badRequest().build();
        }

        var listaUsuarios = usuarioRepositorio.findAll();
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getEmail().equals(data.email())) {
                return ResponseEntity.ok(new LoginResponseDTO(tokenService.generateToken((Usuario) auth.getPrincipal()), usuario));
            }
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.usuarioRepositorio.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());

        Usuario newUser = new Usuario();

        if (data.role() == UserRole.GUEST) {
            newUser = new Usuario(data.email(), encryptedPassword, data.role(), data.nome());
        }

        if (data.role() == UserRole.USER || data.role() == null){
            newUser = new Usuario(data.email(), encryptedPassword, data.role(), data.nome());
        }
        if (data.role() == UserRole.ADMIN) {
            return ResponseEntity.badRequest().build();
        }

        this.usuarioRepositorio.save(newUser);

        return login(new AuthenticationDTO(data.email(), data.senha()));
    }
}
