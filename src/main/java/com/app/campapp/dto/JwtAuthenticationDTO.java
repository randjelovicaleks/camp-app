package com.app.campapp.dto;

public class JwtAuthenticationDTO {

    private String email;
    private String password;

    public JwtAuthenticationDTO() {
    }

    public JwtAuthenticationDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
