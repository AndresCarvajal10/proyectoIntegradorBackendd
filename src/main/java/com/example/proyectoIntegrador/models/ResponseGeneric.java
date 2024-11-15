package com.example.proyectoIntegrador.models;

import lombok.Data;

@Data
public class ResponseGeneric {

    private String responseCode;
    private String responseDesc;
    private Object responseObj;

//    public ResponseGeneric(String code, String desc, Object obj) {
//        this.responseCode = code;
//        this.responseDesc = desc;
//        this.responseObj = obj;
//    }

}
