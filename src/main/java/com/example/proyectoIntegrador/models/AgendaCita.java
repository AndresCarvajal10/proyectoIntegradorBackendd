package com.example.proyectoIntegrador.models;

import lombok.Data;

@Data
public class AgendaCita {
    private int agendaCitaId;    // agenda_cita_id
    private int mascotaId;    // agenda_cita_id
    private String fechaInicio; // fecha_inicio
    private String horaInicio;
    private String estado;       // estado
    private String descripcion;  // descripcion
}
