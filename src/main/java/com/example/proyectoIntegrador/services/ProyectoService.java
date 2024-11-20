package com.example.proyectoIntegrador.services;

import com.example.proyectoIntegrador.models.InfoUser;
import com.example.proyectoIntegrador.models.LoginDTO;
import com.example.proyectoIntegrador.models.RegisterDTO;
import com.example.proyectoIntegrador.models.ResponseRegister;

public interface ProyectoService {

    /**
     * Autentica a un usuario basado en las credenciales de inicio de sesión proporcionadas.
     *
     * @param loginDTO el objeto de transferencia de datos de inicio de sesión que contiene el nombre de usuario y la contraseña
     * @return un objeto InfoUser que contiene la información del usuario si la autenticación es exitosa
     * @throws RuntimeException si el usuario no está registrado o la contraseña es inválida
     */
    InfoUser loginService(LoginDTO loginDTO);

    /**
     * Registra un nuevo usuario en el sistema utilizando los detalles de registro proporcionados.
     *
     * @param registerDTO el objeto de transferencia de datos que contiene los detalles de registro del usuario,
     *                    incluyendo nombre de usuario, contraseña y otra información personal.
     * @return un objeto ResponseRegister que contiene el número de sesión asignado al usuario.
     * @throws RuntimeException si ocurre un error durante el registro del usuario, como fallos al guardar los datos del usuario o la contraseña.
     */
    ResponseRegister registerUser(RegisterDTO registerDTO);

}
