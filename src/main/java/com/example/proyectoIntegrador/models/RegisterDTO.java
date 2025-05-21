package com.example.proyectoIntegrador.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDTO {
    @NotBlank(message = "you must specify the nombre field")
    private String nombre;
    @NotBlank(message = "you must specify the apellido field")
    private String apellido;
    @NotBlank(message = "you must specify the direccion field")
    private String direccion;
    @NotBlank(message = "you must specify the telefono field")
    private String telefono;
    @NotBlank(message = "you must specify the email field")
    private String email;
    private LocalDate fechaRegistro;
    @NotBlank(message = "you must specify the username field")
    private String username;
    @NotBlank(message = "you must specify the password field")
    private String password;
    @NotBlank(message = "you must specify the rolUser field")
    private int rolUser;
}
