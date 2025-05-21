package com.example.proyectoIntegrador.services.Impl;

import com.example.proyectoIntegrador.models.InfoUser;
import com.example.proyectoIntegrador.models.LoginDTO;
import com.example.proyectoIntegrador.models.RegisterDTO;
import com.example.proyectoIntegrador.models.ResponseRegister;
import com.example.proyectoIntegrador.repository.ProyectoRepository;
import com.example.proyectoIntegrador.services.ProyectoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;

    @Override
    public InfoUser loginService(LoginDTO loginDTO) {

        var infoUser = proyectoRepository.validateUser(loginDTO.getUsername())
                .orElseThrow(() -> {
                    log.debug("Error. El usuario no esta registrado");
                    return new RuntimeException("Error. El usuario no esta registrado");
                });

        proyectoRepository.validatePassword(infoUser.getIdClient(), loginDTO.getPassword())
                .orElseThrow(() -> {
                    log.debug("Error. Contraseña no valida");
                    return new RuntimeException("Error. Contraseña no valida");
                });

        return infoUser;

    }

    @Override
    public ResponseRegister registerUser(RegisterDTO registerDTO) {
        Random random = new Random();
        ResponseRegister responseRegister = new ResponseRegister();

        log.debug("start registerUser");

        try {

            // Obtener la fecha de hoy
            LocalDate today = LocalDate.now();

            // Crear un formateador para el nuevo formato
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Cambiar a formato yyyyMMdd

            // Formatear la fecha en el nuevo formato
            today.format(formatter);

            //Registro de solo usuarios
            registerDTO.setFechaRegistro(LocalDate.parse(today.format(formatter)));
            proyectoRepository.registerUser(registerDTO)
                    .orElseThrow(() -> new RuntimeException("Error al registrar usuario"));

            var idUser = proyectoRepository.getIdUser(registerDTO.getUsername()).orElseThrow(() -> new RuntimeException("Error. No se encontro el usuario"));

            proyectoRepository.savePasswordUser(idUser, registerDTO.getPassword()).orElseThrow(() -> new RuntimeException("Error, al guardar la contraseña"));

            int numeroAleatorio = random.nextInt(100); // 0 a 99
            proyectoRepository.setSessiones(numeroAleatorio, idUser);
            responseRegister.setSession(numeroAleatorio);

            return responseRegister;

        } catch (Exception e) {
            log.debug("Error al crear usuario: {}", e.getMessage());
            throw new RuntimeException();
        }

    }

}
