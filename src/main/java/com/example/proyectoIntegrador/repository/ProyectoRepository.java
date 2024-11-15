package com.example.proyectoIntegrador.repository;


import com.example.proyectoIntegrador.models.RegisterClient;
import com.example.proyectoIntegrador.models.RegisterDTO;

import java.util.Optional;

public interface ProyectoRepository {

    Optional<Integer> registerUser(RegisterDTO registerDTO);

    Optional<RegisterClient> getInfoUser(RegisterDTO registerDTO);

    Optional<Integer> setSessiones(int codeSession, int userId);

    Optional<Integer> getIdUser(String userName);

}
