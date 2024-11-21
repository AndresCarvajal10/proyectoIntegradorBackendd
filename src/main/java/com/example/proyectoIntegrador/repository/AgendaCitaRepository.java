package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.models.AgendaCita;

import java.util.List;
import java.util.Optional;

public interface AgendaCitaRepository {

    Optional<List<AgendaCita>> getListAgenda(int idClient);

    public int insertAgendaCita(AgendaCita agendaCita);
}
