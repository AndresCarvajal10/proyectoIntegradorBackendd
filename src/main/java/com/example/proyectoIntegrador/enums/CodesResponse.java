package com.example.proyectoIntegrador.enums;

import lombok.Getter;

@Getter
public enum CodesResponse {

    OK("0000", "STATUS OK"),
    ERROR_NOT_LOGIN("1231", "Error. La contraseña o el usuario son incorrectos"),
    USER_EXISTS("9994", "User already exists"),
    ERROR_REGISTER_USER("6611", "Error al registrar usuario");


    private final String code;
    private final String description;

    private CodesResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }

}
