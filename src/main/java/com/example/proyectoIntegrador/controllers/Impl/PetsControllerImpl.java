package com.example.proyectoIntegrador.controllers.Impl;

import com.example.proyectoIntegrador.controllers.PetsController;
import com.example.proyectoIntegrador.enums.CodesResponse;
import com.example.proyectoIntegrador.models.DataMascotaDTO;
import com.example.proyectoIntegrador.models.DataUserDTO;
import com.example.proyectoIntegrador.models.ResponseGeneric;
import com.example.proyectoIntegrador.models.ResponseLogin;
import com.example.proyectoIntegrador.services.PetsServices;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class PetsControllerImpl implements PetsController {

    private final PetsServices petsServices;

    /**
     * @param dataMascotaDTO
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<ResponseGeneric> addPets(DataMascotaDTO dataMascotaDTO, HttpServletRequest request) {
        log.debug("START addPets");

        try {
            var userInfoLogin = petsServices.addPet(dataMascotaDTO);

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

    /**
     * @param dataUserDTO
     * @param request
     * @return
     */
    @Override
    public ResponseEntity<ResponseGeneric> getListPets(DataUserDTO dataUserDTO, HttpServletRequest request) {
        log.debug("START addPets");

        try {
            var userInfoLogin = petsServices.getListPets(dataUserDTO);

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
