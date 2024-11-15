package com.example.proyectoIntegrador.services;

import com.example.proyectoIntegrador.models.LoginDTO;
import com.example.proyectoIntegrador.models.RegisterDTO;
import com.example.proyectoIntegrador.models.ResponseLogin;
import com.example.proyectoIntegrador.models.ResponseRegister;

public interface ProyectoService {

     ResponseLogin loginService(LoginDTO loginDTO);

     ResponseRegister registerUser(RegisterDTO registerDTO);
}
