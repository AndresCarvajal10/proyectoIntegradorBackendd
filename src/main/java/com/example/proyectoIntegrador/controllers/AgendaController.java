package com.example.proyectoIntegrador.controllers;

import com.example.proyectoIntegrador.models.DataAppointmentDTO;
import com.example.proyectoIntegrador.models.DataUserDTO;
import com.example.proyectoIntegrador.models.ResponseGeneric;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/integrador/agendaCita")
public interface AgendaController {

    /**
     * Maneja las solicitudes de inicio de sesión de los usuarios validando las credenciales proporcionadas.
     *
     * @param loginDTO el objeto de transferencia de datos que contiene las credenciales de inicio de sesión del usuario
     * @param request  el objeto de solicitud HTTP
     * @return un ResponseEntity que contiene un objeto ResponseLogin con la información del usuario si el inicio de sesión es exitoso,
     * o un mensaje de error si el inicio de sesión falla
     */
    @GetMapping("/list")
    @ResponseBody
    ResponseEntity<ResponseGeneric> getListAgend(@ModelAttribute @Valid DataUserDTO loginDTO, HttpServletRequest request);



    @PostMapping("/scheduleAnAppointment")
    @ResponseBody
    ResponseEntity<ResponseGeneric> scheduleAnAppointment(@Valid @RequestBody DataAppointmentDTO dataAppointmentDTO, HttpServletRequest request);


}
