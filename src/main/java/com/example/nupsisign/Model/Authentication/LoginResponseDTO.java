package com.example.nupsisign.Model.Authentication;
import com.example.nupsisign.Model.Dbset.Usuario;


public record LoginResponseDTO(String token, Usuario usuario) {
}
