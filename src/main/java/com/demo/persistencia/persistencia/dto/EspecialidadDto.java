package com.demo.persistencia.persistencia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EspecialidadDto {
    
    private Long IDespecialidad;
    private String nombre;
    private String estado;
}

