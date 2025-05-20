package com.example.proyectoIntegrador.models;

import com.example.proyectoIntegrador.models.selectors.SelectorEstado;
import com.example.proyectoIntegrador.models.selectors.SelectorRaza;
import com.example.proyectoIntegrador.models.selectors.SelectorRols;
import com.example.proyectoIntegrador.models.selectors.SelectorVeterinarian;
import lombok.Data;

import java.util.List;

@Data
public class DataSelector {
    List<SelectorEstado> selectorEstadoList;
    List<SelectorVeterinarian> selectorVeterinarians;
    List<SelectorRaza> selectorRazas;
    List<SelectorRols> selectorRols;
}
