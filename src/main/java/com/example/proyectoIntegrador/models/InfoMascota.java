package com.example.proyectoIntegrador.models;

import lombok.Data;

@Data
public class InfoMascota {
    private int idMascota;
    private int codigo;
    private String nombreMascota;
    private String tipoRaza;
    private int edad;
}
