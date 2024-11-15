package com.example.proyectoIntegrador.controllers;

import com.example.proyectoIntegrador.models.LoginDTO;
import com.example.proyectoIntegrador.models.RegisterDTO;
import com.example.proyectoIntegrador.models.ResponseLogin;
import com.example.proyectoIntegrador.models.ResponseRegister;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/integrador")
public interface ProyectoController {
    @PostMapping("/login")
    @ResponseBody
    ResponseEntity<ResponseLogin> login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request);

    @PostMapping("/register")
    @ResponseBody
    ResponseEntity<ResponseRegister> register(@Valid @RequestBody RegisterDTO registerDTO, HttpServletRequest request);

}
