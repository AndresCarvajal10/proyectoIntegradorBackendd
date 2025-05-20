package com.example.proyectoIntegrador.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DataUserDTO {
    @NotBlank(message = "required field idClient")
    private int idClient;
}
