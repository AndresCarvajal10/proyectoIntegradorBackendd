package com.example.proyectoIntegrador.controllers;


import com.example.proyectoIntegrador.models.DataUserDTO;
import com.example.proyectoIntegrador.models.ResponseGeneric;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/integrador/selectors")
public interface SelectorsController {

    @GetMapping("/All")
    @ResponseBody
    ResponseEntity<ResponseGeneric> getListSelectors(@ModelAttribute @Valid DataUserDTO loginDTO, HttpServletRequest request);

}
