package com.example.proyectoIntegrador.services;

import com.example.proyectoIntegrador.models.DataMascotaDTO;
import com.example.proyectoIntegrador.models.DataUserDTO;
import com.example.proyectoIntegrador.models.InfoMascota;

import java.util.List;

public interface PetsServices {

    boolean getPetExists(String petId);

    boolean addPet(DataMascotaDTO dataMascotaDTO);

    List<InfoMascota> getListPets(DataUserDTO dataUserDTO);
}
