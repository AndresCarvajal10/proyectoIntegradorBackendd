package com.example.proyectoIntegrador.models;

import lombok.Data;

@Data
public class DataMascotaDTO {
    private final Integer idCliente;
    private final String nombre;
    private final Integer edad;
    private final String genero;
    private final Integer razaId;
}
