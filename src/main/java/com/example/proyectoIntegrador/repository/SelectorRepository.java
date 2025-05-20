package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.models.selectors.SelectorEstado;
import com.example.proyectoIntegrador.models.selectors.SelectorRaza;
import com.example.proyectoIntegrador.models.selectors.SelectorRols;
import com.example.proyectoIntegrador.models.selectors.SelectorVeterinarian;

import java.util.List;
import java.util.Optional;

public interface SelectorRepository {

    Optional<List<SelectorEstado>> getSelectorEstado();

    Optional<List<SelectorVeterinarian>> getSelectorVeterinarian();

    Optional<List<SelectorRaza>> getSelectorRaza();

    Optional<List<SelectorRols>> getSelectorRols();
}
