package com.example.proyectoIntegrador.services.Impl;

import com.example.proyectoIntegrador.models.DataMascotaDTO;
import com.example.proyectoIntegrador.repository.AgendaCitaRepository;
import com.example.proyectoIntegrador.repository.PetsRepository;
import com.example.proyectoIntegrador.services.PetsServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

        var existPet = petsRepository.validateExistPet(dataMascotaDTO.getNombre(), dataMascotaDTO.getEdad(), dataMascotaDTO.getGenero(), dataMascotaDTO.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Error. No se pudo validar la existencia de la mascota con los datos ingresados"));

        if (existPet) {
            log.debug("Existe la mascota con los datos ingresados");
            throw new RuntimeException("La mascota ya existe");
        } else {
            log.debug("No existe la mascota con los datos ingresados");
        }

        String codigo = UUID.randomUUID().toString().substring(0, 8);

        petsRepository.addPet(dataMascotaDTO.getIdCliente(), codigo, dataMascotaDTO.getNombre(),
                        dataMascotaDTO.getEdad(), dataMascotaDTO.getGenero(), dataMascotaDTO.getRazaId())
                .orElseThrow(() -> new RuntimeException("No se pudo agregar la mascota"));

        return true;
    }
}
