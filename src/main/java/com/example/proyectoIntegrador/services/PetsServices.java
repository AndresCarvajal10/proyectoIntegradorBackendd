package com.example.proyectoIntegrador.services;

import com.example.proyectoIntegrador.models.DataMascotaDTO;

public interface PetsServices {

    boolean getPetExists(String petId);

    boolean addPet(DataMascotaDTO dataMascotaDTO);
}
