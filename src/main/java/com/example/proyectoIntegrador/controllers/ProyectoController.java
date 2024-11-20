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

    /**
     * Maneja las solicitudes de inicio de sesión de los usuarios validando las credenciales proporcionadas.
     *
     * @param loginDTO el objeto de transferencia de datos que contiene las credenciales de inicio de sesión del usuario
     * @param request  el objeto de solicitud HTTP
     * @return un ResponseEntity que contiene un objeto ResponseLogin con la información del usuario si el inicio de sesión es exitoso,
     * o un mensaje de error si el inicio de sesión falla
     */
    @PostMapping("/login")
    @ResponseBody
    ResponseEntity<ResponseLogin> login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest request);


    /**
     * Maneja el registro de un nuevo usuario.
     *
     * @param registerDTO el objeto de transferencia de datos que contiene los detalles de registro del usuario
     * @param request     el objeto de solicitud HTTP
     * @return un ResponseEntity que contiene un objeto ResponseRegister con el resultado del registro
     * y el código de estado HTTP apropiado
     * @throws Exception si ocurre un error durante el registro del usuario, devuelve un estado no autorizado
     */
    @PostMapping("/register")
    @ResponseBody
    ResponseEntity<ResponseRegister> register(@Valid @RequestBody RegisterDTO registerDTO, HttpServletRequest request);

}
