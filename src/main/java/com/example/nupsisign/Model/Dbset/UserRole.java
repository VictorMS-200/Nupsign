package com.example.nupsisign.Model.Dbset;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER"),
    GUEST("GUEST");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
