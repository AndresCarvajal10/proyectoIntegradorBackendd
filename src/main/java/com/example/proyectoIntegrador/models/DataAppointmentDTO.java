package com.example.proyectoIntegrador.models;

import lombok.Data;

@Data
public class DataAppointmentDTO {
    private int estadoId;
    private int clienteId;
    private int citaId;
    private int medicoId;
    private String description;
    private String fecha;
    private String hora;
}
