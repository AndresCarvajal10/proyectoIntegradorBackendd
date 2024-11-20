package com.example.proyectoIntegrador.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "you must specify the username field")
    private String username;
    @NotBlank(message = "you must specify the password field")
    private String password;
}
