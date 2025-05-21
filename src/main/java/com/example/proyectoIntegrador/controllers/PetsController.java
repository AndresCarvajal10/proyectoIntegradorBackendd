package com.example.proyectoIntegrador.controllers;

import com.example.proyectoIntegrador.models.DataMascotaDTO;
import com.example.proyectoIntegrador.models.DataUserDTO;
import com.example.proyectoIntegrador.models.ResponseGeneric;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/integrador/pets")
public interface PetsController {

    @PostMapping("/Addpets")
    @ResponseBody
    ResponseEntity<ResponseGeneric> addPets(@Valid @RequestBody DataMascotaDTO dataMascotaDTO, HttpServletRequest request);


    @GetMapping("/getListPets")
    @ResponseBody
    ResponseEntity<ResponseGeneric> getListPets(@ModelAttribute @Valid DataUserDTO dataUserDTO, HttpServletRequest request);

}
