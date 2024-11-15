package com.example.proyectoIntegrador.controllers.Impl;

import com.example.proyectoIntegrador.controllers.ProyectoController;
import com.example.proyectoIntegrador.models.LoginDTO;
import com.example.proyectoIntegrador.models.RegisterDTO;
import com.example.proyectoIntegrador.models.ResponseLogin;
import com.example.proyectoIntegrador.models.ResponseRegister;
import com.example.proyectoIntegrador.services.ProyectoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@Slf4j
public class ProyectoControllerImpl implements ProyectoController {

    private final ProyectoService proyectoService;

    @Override
    public ResponseEntity<ResponseLogin> login(LoginDTO loginDTO, HttpServletRequest request) {

        log.debug("START login");

        try {
            ResponseLogin responseLogin = proyectoService.loginService(loginDTO);
            return ResponseEntity.ok().body(responseLogin);

        } catch (Exception e) {
            ResponseLogin responseError = new ResponseLogin();
            responseError.setResponseCode("9999");
            responseError.setResponseDesc(e.getMessage().toString());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseError);
        }
    }

    @Override
    public ResponseEntity<ResponseRegister> register(RegisterDTO registerDTO, HttpServletRequest request) {

        log.debug("START register");

        try {

            ResponseRegister responseLogin = proyectoService.registerUser(registerDTO);
            return ResponseEntity.ok().body(responseLogin);

        } catch (Exception e) {
            ResponseRegister responseError = new ResponseRegister();
            responseError.setResponseCode("9999");
            responseError.setResponseDesc(e.getMessage().toString());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseError);
        }
    }
}
