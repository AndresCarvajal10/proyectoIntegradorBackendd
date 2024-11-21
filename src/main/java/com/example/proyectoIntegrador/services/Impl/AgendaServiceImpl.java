package com.example.proyectoIntegrador.services.Impl;

import com.example.proyectoIntegrador.models.AgendaCita;
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
        return agendaCitaRepository.getListAgenda(idClient).orElse(List.of());
    }
}
