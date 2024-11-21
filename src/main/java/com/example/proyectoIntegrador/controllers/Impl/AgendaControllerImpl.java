package com.example.proyectoIntegrador.controllers.Impl;

import com.example.proyectoIntegrador.controllers.AgendaController;
import com.example.proyectoIntegrador.enums.CodesResponse;
import com.example.proyectoIntegrador.models.InfoUser;
import com.example.proyectoIntegrador.models.ResponseGeneric;
import com.example.proyectoIntegrador.models.ResponseLogin;
import com.example.proyectoIntegrador.models.dataUserDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AgendaControllerImpl implements AgendaController {
    @Override
    public ResponseEntity<ResponseGeneric> getListAgend(dataUserDTO dataUser, HttpServletRequest request) {
        log.debug("START getListAgend");

        try {
            InfoUser userInfoLogin = null;//proyectoService.loginService(dataUser);

            ResponseLogin responseLogin = new ResponseLogin();
            responseLogin.setResponseCode(CodesResponse.OK.getCode());
            responseLogin.setResponseDesc(CodesResponse.OK.getDescription());
            responseLogin.setResponseObj(userInfoLogin);

            return ResponseEntity.ok().body(responseLogin);

        } catch (Exception e) {
            ResponseLogin responseError = new ResponseLogin();
            responseError.setResponseCode(CodesResponse.ERROR_NOT_LOGIN.getCode());
            responseError.setResponseDesc(CodesResponse.ERROR_NOT_LOGIN.getDescription());
            responseError.setResponseObj(e.getMessage());

            return ResponseEntity.status(HttpStatus.OK).body(responseError);
        }
    }
}
