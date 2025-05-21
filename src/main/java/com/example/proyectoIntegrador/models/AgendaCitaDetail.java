package com.example.proyectoIntegrador.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AgendaCitaDetail extends AgendaCita {

    private InfoUser infoUser;
    private InfoMascota infoMascota;
    private String nombreVeterinario;

}
