package com.example.proyectoIntegrador.repository;

import java.util.Optional;

public interface PetsRepository {

    Optional<Integer> validateExistPet(String petName);

    Optional<Boolean> addPet(Integer clientId, String petName, Integer petAge, String petGender, Integer razaId);
}
