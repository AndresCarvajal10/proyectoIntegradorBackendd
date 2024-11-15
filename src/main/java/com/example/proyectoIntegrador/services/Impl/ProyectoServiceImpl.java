package com.example.proyectoIntegrador.services.Impl;

import com.example.proyectoIntegrador.models.LoginDTO;
import com.example.proyectoIntegrador.models.RegisterDTO;
import com.example.proyectoIntegrador.models.ResponseLogin;
import com.example.proyectoIntegrador.models.ResponseRegister;
import com.example.proyectoIntegrador.repository.ProyectoRepository;
import com.example.proyectoIntegrador.services.ProyectoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;

    @Override
    public ResponseLogin loginService(LoginDTO loginDTO) {


        return null;
    }

    @Override
    public ResponseRegister registerUser(RegisterDTO registerDTO) {
        Random random = new Random();
        ResponseRegister responseRegister = new ResponseRegister();

        log.debug("start registerUser");

        try {

            proyectoRepository.registerUser(registerDTO).orElseThrow(()-> new RuntimeException("Error. Al registrar usuario"));

            var idUser = proyectoRepository.getIdUser(registerDTO.getUsername()).orElseThrow(()-> new RuntimeException("Error. No se encontro el usuario"));

            int numeroAleatorio = random.nextInt(100); // 0 a 99
            proyectoRepository.setSessiones(numeroAleatorio, idUser);
            responseRegister.setSession(numeroAleatorio);

            return responseRegister;

        } catch (Exception e) {
            log.debug("Error al crear usuario: {}", e.getMessage());
            throw new RuntimeException("Error. Al registrar usuario");
        }

    }
}
