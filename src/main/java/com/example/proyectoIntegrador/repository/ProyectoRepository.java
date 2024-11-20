package com.example.proyectoIntegrador.repository;


import com.example.proyectoIntegrador.models.*;

import java.util.Optional;

public interface ProyectoRepository {

    Optional<Integer> registerUser(RegisterDTO registerDTO);

    Optional<RegisterClient> getInfoUser(RegisterDTO registerDTO);

    Optional<Integer> setSessiones(int codeSession, int userId);

    Optional<Integer> getIdUser(String userName);

    Optional<InfoUser> validateUser(String username);

    Optional<Boolean> validatePassword(String idClient, String password);

    public Optional<Integer> savePasswordUser(int idClient, String password);

}
