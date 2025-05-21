package com.example.proyectoIntegrador.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DataDetailAppointmentDTO {
    @NotBlank(message = "required field idAppointment")
    private final Integer idAppointment;
    @NotBlank(message = "required field idClient")
    private final Integer idClient;
    @NotBlank(message = "required field username")
    private final String username;
}
