package com.example.proyectoIntegrador.models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AgendaCita {
    private int agendaCitaId;    // agenda_cita_id
    private int usuarioId;       // usuario_id
    private int empleadoId;      // empleado_id
    private int citaId;          // cita_id
    private Timestamp fechaInicio; // fecha_inicio
    private String estado;       // estado
    private String descripcion;  // descripcion
    private String direccion;    // direccion
    private Integer telefono;    // telefono
}
