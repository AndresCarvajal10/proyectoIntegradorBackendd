package com.example.proyectoIntegrador.controllers.Impl;

import com.example.proyectoIntegrador.controllers.SelectorsController;
import com.example.proyectoIntegrador.enums.CodesResponse;
import com.example.proyectoIntegrador.models.DataSelector;
import com.example.proyectoIntegrador.models.DataUserDTO;
import com.example.proyectoIntegrador.models.ResponseGeneric;
import com.example.proyectoIntegrador.models.ResponseLogin;
import com.example.proyectoIntegrador.services.SelectorsServices;
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
public class SelectorsControllerImpl implements SelectorsController {

    private final SelectorsServices selectorsServices;

    @Override
    public ResponseEntity<ResponseGeneric> getListSelectors(DataUserDTO dataUserDTO, HttpServletRequest request) {
        log.debug("START getListSelectors");

        try {
            DataSelector userInfoLogin = selectorsServices.getSelectorDetail(dataUserDTO);

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
}
