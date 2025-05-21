package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.models.InfoMascota;

import java.util.List;
import java.util.Optional;


public interface PetsRepository {

    Optional<Boolean> validateExistPet(String nombre, int edad, String genero, Integer clienteId);

    Optional<Boolean> addPet(Integer clientId, String petCodigo, String petName, Integer petAge, String petGender, Integer razaId);

    Optional<List<InfoMascota>> getListPetsByUser(int idClient);

}
