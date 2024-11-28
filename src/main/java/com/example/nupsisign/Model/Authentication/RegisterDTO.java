package com.example.nupsisign.Model.Authentication;

import com.example.nupsisign.Model.Dbset.UserRole;

public record RegisterDTO(String nome, String email, String senha, UserRole role) {
}
