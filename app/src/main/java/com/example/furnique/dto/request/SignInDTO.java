package com.example.furnique.dto.request;

public class SignInDTO {
    private String email;
    private String password;

    public SignInDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public SignInDTO() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
