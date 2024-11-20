package com.example.proyectoIntegrador.services;

import com.example.proyectoIntegrador.models.*;

public interface ProyectoService {

     InfoUser loginService(LoginDTO loginDTO);

     ResponseRegister registerUser(RegisterDTO registerDTO);

}
