package com.example.proyectoIntegrador.controllers;

import com.example.proyectoIntegrador.models.ResponseLogin;
import com.example.proyectoIntegrador.models.ResponseRegister;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ProyectoController {
    public ResponseLogin login();
    public ResponseRegister register();

}
