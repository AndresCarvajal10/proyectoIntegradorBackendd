package com.example.proyectoIntegrador.controllers.Impl;

import com.example.proyectoIntegrador.controllers.ProyectoController;
import com.example.proyectoIntegrador.enums.CodesResponse;
import com.example.proyectoIntegrador.models.*;
import com.example.proyectoIntegrador.services.ProyectoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ProyectoControllerImpl implements ProyectoController {

    private final ProyectoService proyectoService;

    @Override
    public ResponseEntity<ResponseLogin> login(LoginDTO loginDTO, HttpServletRequest request) {

        log.debug("START login");

        try {
            InfoUser userInfoLogin = proyectoService.loginService(loginDTO);

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

    @Override
    public ResponseEntity<ResponseRegister> register(RegisterDTO registerDTO, HttpServletRequest request) {

        log.debug("START register");

        try {
            ResponseRegister responseRegister = proyectoService.registerUser(registerDTO);
            responseRegister.setResponseCode(CodesResponse.OK.getCode());
            responseRegister.setResponseDesc(CodesResponse.OK.getDescription());
            return ResponseEntity.ok().body(responseRegister);

        } catch (Exception e) {
            ResponseRegister responseError = new ResponseRegister();
            responseError.setResponseCode(CodesResponse.ERROR_REGISTER_USER.getCode());
            responseError.setResponseDesc(CodesResponse.ERROR_REGISTER_USER.getDescription());
            responseError.setResponseObj(e.getMessage());

            return ResponseEntity.status(HttpStatus.OK).body(responseError);
        }
    }
}
