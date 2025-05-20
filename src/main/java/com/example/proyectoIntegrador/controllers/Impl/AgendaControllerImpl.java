package com.example.proyectoIntegrador.controllers.Impl;

import com.example.proyectoIntegrador.controllers.AgendaController;
import com.example.proyectoIntegrador.enums.CodesResponse;
import com.example.proyectoIntegrador.models.*;
import com.example.proyectoIntegrador.services.AgendaService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AgendaControllerImpl implements AgendaController {

    private final AgendaService agendaService;

    @Override
    public ResponseEntity<ResponseGeneric> getListAgend(DataUserDTO dataUser, HttpServletRequest request) {
        log.debug("START getListAgend");

        try {
            List<AgendaCita> userInfoLogin = agendaService.getListCitas(dataUser.getIdClient());

            ResponseGeneric responseLogin = new ResponseGeneric();
            responseLogin.setResponseCode(CodesResponse.OK.getCode());
            responseLogin.setResponseDesc(CodesResponse.OK.getDescription());
            responseLogin.setResponseObj(userInfoLogin);

            return ResponseEntity.ok().body(responseLogin);

        } catch (Exception e) {
            ResponseLogin responseError = new ResponseLogin();
            responseError.setResponseCode(CodesResponse.ERROR_GET_DATA.getCode());
            responseError.setResponseDesc(CodesResponse.ERROR_GET_DATA.getDescription());
            responseError.setResponseObj(e.getMessage());

            return ResponseEntity.status(HttpStatus.OK).body(responseError);
        }
    }

    @Override
    public ResponseEntity<ResponseGeneric> scheduleAnAppointment(DataAppointmentDTO dataAppointmentDTO, HttpServletRequest request) {
        log.debug("START scheduleAnAppointment");

        try {
            Boolean userInfoLogin = agendaService.scheduleAnAppointment(dataAppointmentDTO);

            ResponseGeneric responseLogin = new ResponseGeneric();
            responseLogin.setResponseCode(CodesResponse.OK.getCode());
            responseLogin.setResponseDesc(CodesResponse.OK.getDescription());
            responseLogin.setResponseObj(userInfoLogin);

            return ResponseEntity.ok().body(responseLogin);

        } catch (Exception e) {
            ResponseLogin responseError = new ResponseLogin();
            responseError.setResponseCode(CodesResponse.ERROR_SAVE_AGEND.getCode());
            responseError.setResponseDesc(CodesResponse.ERROR_SAVE_AGEND.getDescription());
            responseError.setResponseObj(e.getMessage());

            return ResponseEntity.status(HttpStatus.OK).body(responseError);
        }
    }
}
