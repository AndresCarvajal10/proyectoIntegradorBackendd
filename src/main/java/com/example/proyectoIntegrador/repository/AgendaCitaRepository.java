package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.models.AgendaCita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AgendaCitaRepository {

    Optional<List<AgendaCita>> getListAgenda(int idClient);

    Optional<Boolean> validateClient(int idClient);

    Optional<Boolean> validateVeterinarian(int idVeterinarian);

    Optional<Integer> insertAgendaCita(String description, String fecha, String hora, Integer estadoId, Integer clienteId, Integer medicoId);

    Optional<Boolean> actualizarFechaHoraCita(Integer citaId, String nuevaFecha, String nuevaHora, Integer medicoId);

    Optional<Boolean> cancelarCita(Integer citaId);

    Optional<Boolean> hayDisponibilidadCita(LocalDate fechaDeseada, LocalTime horaDeseada);
}
