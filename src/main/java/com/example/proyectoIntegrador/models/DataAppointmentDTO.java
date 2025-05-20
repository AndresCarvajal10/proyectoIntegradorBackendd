package com.example.proyectoIntegrador.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DataAppointmentDTO {
    @NotBlank(message = "required field estadoId")
    private int estadoId;
    @NotBlank(message = "required field clienteId")
    private int clienteId;
    @NotBlank(message = "required field medicoId")
    private int medicoId;
    @NotBlank(message = "required field description")
    private String description;
    @NotBlank(message = "required field fecha")
    private String fecha;
    @NotBlank(message = "required field hora")
    private String hora;
}
