package com.example.proyectoIntegrador.services.Impl;

import com.example.proyectoIntegrador.models.DataMascotaDTO;
import com.example.proyectoIntegrador.repository.AgendaCitaRepository;
import com.example.proyectoIntegrador.repository.PetsRepository;
import com.example.proyectoIntegrador.services.PetsServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PetsServicesImpl implements PetsServices {

    private final PetsRepository petsRepository;
    private final AgendaCitaRepository agendaCitaRepository;

    /**
     * @param petId
     * @return
     */
    @Override
    public boolean getPetExists(String petId) {
        return false;
    }

    /**
     * @param dataMascotaDTO
     * @return
     */
    @Override
    public boolean addPet(DataMascotaDTO dataMascotaDTO) {

        agendaCitaRepository.validateClient(dataMascotaDTO.getIdCliente())
                .orElseThrow(() -> new RuntimeException("cliente not found"));

        petsRepository.validateExistPet(dataMascotaDTO.getNombre())
                .orElseThrow(() -> new RuntimeException("Ya existe un mascota existente"));

        petsRepository.addPet(dataMascotaDTO.getIdCliente(), dataMascotaDTO.getNombre(),
                        dataMascotaDTO.getEdad(), dataMascotaDTO.getGenero(), dataMascotaDTO.getRazaId())
                .orElseThrow(() -> new RuntimeException("No se pudo agregar la mascota"));

        return true;
    }
}
