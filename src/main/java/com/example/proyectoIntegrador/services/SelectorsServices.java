package com.example.proyectoIntegrador.services;

import com.example.proyectoIntegrador.models.DataSelector;
import com.example.proyectoIntegrador.models.DataUserDTO;

public interface SelectorsServices {

    DataSelector getSelectorDetail(DataUserDTO dataUserDTO);
}
