package com.example.proyectoIntegrador.services;

import com.example.proyectoIntegrador.models.AgendaCita;

import java.util.List;

public interface AgendaService {

    List<AgendaCita> getListCitas(int idClient);
}
