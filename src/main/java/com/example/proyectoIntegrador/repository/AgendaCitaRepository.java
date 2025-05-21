package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.models.AgendaCita;
import com.example.proyectoIntegrador.models.AgendaCitaDetail;
import com.example.proyectoIntegrador.models.InfoMascota;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AgendaCitaRepository {

    Optional<List<AgendaCita>> getListAgenda(int idClient);

    Optional<Boolean> validateClient(int idClient);

    Optional<Boolean> validateMascota(int mascotaId);

    Optional<Boolean> validateVeterinarian(int idVeterinarian);

    Optional<Integer> insertAgendaCita(String description, String fecha, String hora, Integer estadoId, Integer clienteId, Integer medicoId, Integer mascotaId);

    Optional<Boolean> actualizarFechaHoraCita(Integer citaId, String nuevaFecha, String nuevaHora, Integer medicoId);

    Optional<Boolean> cancelarCita(Integer citaId, Integer clienteId);

    Optional<Boolean> hayDisponibilidadCita(LocalDate fechaDeseada, LocalTime horaDeseada);

    Optional<AgendaCitaDetail> getDetailAppointment(int idAgenda, int idClient);

    Optional<InfoMascota> getInfoMascota(int idClient, int mascotaId);

    Optional<Boolean> validateExistAppointment(int idAppointment, int idClient);
}
