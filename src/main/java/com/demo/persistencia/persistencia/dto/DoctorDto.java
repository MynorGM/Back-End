package com.demo.persistencia.persistencia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorDto {
    
    private String nombreCompleto;
    private Long IDespecialidad; // Se asume que solo se necesita el ID de la especialidad para algunas operaciones
    private String especialidadNombre; // Para mostrar el nombre de la especialidad en la UI sin tener que buscarlo por ID
    private String estado;
}
