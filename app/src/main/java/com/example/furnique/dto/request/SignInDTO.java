package com.example.furnique.dto.request;

public class SignInDTO {
    private String email;
    private String password;

    public SignInDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
