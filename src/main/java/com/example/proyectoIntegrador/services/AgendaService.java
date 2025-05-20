package com.example.proyectoIntegrador.services;

import com.example.proyectoIntegrador.models.AgendaCita;
import com.example.proyectoIntegrador.models.DataAppointmentDTO;

import java.util.List;

public interface AgendaService {

    List<AgendaCita> getListCitas(int idClient);

    boolean scheduleAnAppointment(DataAppointmentDTO dataAppointmentDTO);

}
