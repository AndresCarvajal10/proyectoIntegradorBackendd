package com.example.proyectoIntegrador.services.Impl;

import com.example.proyectoIntegrador.models.AgendaCita;
import com.example.proyectoIntegrador.models.DataAppointmentDTO;
import com.example.proyectoIntegrador.repository.AgendaCitaRepository;
import com.example.proyectoIntegrador.services.AgendaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgendaServiceImpl implements AgendaService {

    private final AgendaCitaRepository agendaCitaRepository;

    @Override
    public List<AgendaCita> getListCitas(int idClient) {
        log.debug("start getListCitas");

        agendaCitaRepository.validateClient(idClient)
                .orElseThrow(() -> new RuntimeException("cliente not found"));

        return agendaCitaRepository.getListAgenda(idClient).orElse(List.of());
    }

    @Override
    public boolean scheduleAnAppointment(DataAppointmentDTO dataAppointmentDTO) {
        log.debug("start scheduleAnAppointment");

        agendaCitaRepository.validateClient(dataAppointmentDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("cliente not found"));

        agendaCitaRepository.validateVeterinarian(dataAppointmentDTO.getMedicoId())
                .orElseThrow(() -> new RuntimeException("veterinario not found"));

        agendaCitaRepository.insertAgendaCita(dataAppointmentDTO.getDescription(), dataAppointmentDTO.getFecha(),
                        dataAppointmentDTO.getHora(), dataAppointmentDTO.getEstadoId(), dataAppointmentDTO.getClienteId(), dataAppointmentDTO.getMedicoId())
                .orElseThrow(() -> new RuntimeException("No se pudo agendar la cita"));

        return true;
    }

}
