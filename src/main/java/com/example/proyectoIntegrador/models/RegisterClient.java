package com.example.proyectoIntegrador.models;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RegisterClient {
    private int clienteId;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private LocalDate fechaRegistro;
    private String estadoCliente;
    private String password;
}
