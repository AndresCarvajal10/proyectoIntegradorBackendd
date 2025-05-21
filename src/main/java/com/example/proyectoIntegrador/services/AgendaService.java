package com.example.proyectoIntegrador.services;

import com.example.proyectoIntegrador.models.AgendaCita;
import com.example.proyectoIntegrador.models.AgendaCitaDetail;
import com.example.proyectoIntegrador.models.DataAppointmentDTO;
import com.example.proyectoIntegrador.models.DataDetailAppointmentDTO;

import java.util.List;

public interface AgendaService {

    List<AgendaCita> getListCitas(int idClient);

    boolean scheduleAnAppointment(DataAppointmentDTO dataAppointmentDTO);

    AgendaCitaDetail getDetailAppointment(DataDetailAppointmentDTO detailAppointmentDTO);

}
