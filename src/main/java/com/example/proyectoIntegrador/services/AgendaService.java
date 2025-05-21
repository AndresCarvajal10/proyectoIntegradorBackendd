package com.example.proyectoIntegrador.services;

import com.example.proyectoIntegrador.models.*;

import java.util.List;

public interface AgendaService {

    List<AgendaCita> getListCitas(int idClient);

    boolean scheduleAnAppointment(DataAppointmentDTO dataAppointmentDTO);

    AgendaCitaDetail getDetailAppointment(DataDetailAppointmentDTO detailAppointmentDTO);

    boolean cancelledAppointment(DataCancelledAppointmentDTO dataCancelledAppointmentDTO);
}
