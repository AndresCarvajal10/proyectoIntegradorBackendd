package com.example.proyectoIntegrador.services.Impl;

import com.example.proyectoIntegrador.models.AgendaCita;
import com.example.proyectoIntegrador.models.DataAppointmentDTO;
import com.example.proyectoIntegrador.repository.AgendaCitaRepository;
import com.example.proyectoIntegrador.services.AgendaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

        return agendaCitaRepository.getListAgenda(idClient)
                .orElseThrow(() -> new RuntimeException("El usuario no tiene citas agendadas"));
    }

    @Override
    public boolean scheduleAnAppointment(DataAppointmentDTO dataAppointmentDTO) {
        log.debug("start scheduleAnAppointment");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");  // Cambiar a formato yyyyMMdd
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HHmmss");  // Cambiar a formato HHmmss

        LocalDate date = LocalDate.parse(dataAppointmentDTO.getFecha(), formatter);
        LocalTime time = LocalTime.parse(dataAppointmentDTO.getHora(), formatterTime);

        agendaCitaRepository.validateClient(dataAppointmentDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("cliente not found"));

        agendaCitaRepository.validateVeterinarian(dataAppointmentDTO.getMedicoId())
                .orElseThrow(() -> new RuntimeException("veterinario not found"));

        agendaCitaRepository.hayDisponibilidadCita(date, time)
                .orElseThrow(() -> new RuntimeException("No hay disponibilidad de citas"));

        agendaCitaRepository.insertAgendaCita(dataAppointmentDTO.getDescription(), dataAppointmentDTO.getFecha(),
                        dataAppointmentDTO.getHora(), dataAppointmentDTO.getEstadoId(), dataAppointmentDTO.getClienteId(), dataAppointmentDTO.getMedicoId())
                .orElseThrow(() -> new RuntimeException("No se pudo agendar la cita"));

        return true;
    }

}
