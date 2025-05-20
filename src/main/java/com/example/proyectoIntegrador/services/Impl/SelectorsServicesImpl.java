package com.example.proyectoIntegrador.services.Impl;

import com.example.proyectoIntegrador.models.DataSelector;
import com.example.proyectoIntegrador.models.DataUserDTO;
import com.example.proyectoIntegrador.repository.SelectorRepository;
import com.example.proyectoIntegrador.services.SelectorsServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SelectorsServicesImpl implements SelectorsServices {

    private final SelectorRepository selectorRepository;

    @Override
    public DataSelector getSelectorDetail(DataUserDTO dataUserDTO) {

        DataSelector dataSelectors = new DataSelector();


        var estados = selectorRepository.getSelectorEstado()
                .orElseThrow(() -> {
                    log.error("Error. No hay datos configurados para el estado");
                    return new RuntimeException("Error. No hay datos configurados para el estado");
                });

        var veterinarian = selectorRepository.getSelectorVeterinarian()
                .orElseThrow(() -> {
                    log.error("Error. No hay datos configurados de veteinarios");
                    return new RuntimeException("Error. No hay datos configurados de veteinarios");
                });

        var typeRazas = selectorRepository.getSelectorRaza()
                .orElseThrow(() -> {
                    log.error("Error. No hay tipos de raza configurados");
                    return new RuntimeException("Error. No hay tipos de raza configurados");
                });

        var roles = selectorRepository.getSelectorRols()
                .orElseThrow(() -> {
                    log.error("Error. No hay datos configurados de roles");
                    return new RuntimeException("Error. No hay datos configurados de roles");
                });

        dataSelectors.setSelectorEstadoList(estados);
        dataSelectors.setSelectorVeterinarians(veterinarian);
        dataSelectors.setSelectorRazas(typeRazas);
        dataSelectors.setSelectorRols(roles);

        return dataSelectors;
    }
}
