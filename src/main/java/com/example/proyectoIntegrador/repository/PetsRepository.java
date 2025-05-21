package com.example.proyectoIntegrador.repository;

import java.util.Optional;


public interface PetsRepository {

    Optional<Boolean> validateExistPet(String nombre, int edad, String genero, Integer clienteId);

    Optional<Boolean> addPet(Integer clientId, String petCodigo, String petName, Integer petAge, String petGender, Integer razaId);
}
