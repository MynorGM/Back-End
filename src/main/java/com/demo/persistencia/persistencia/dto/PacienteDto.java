package com.demo.persistencia.persistencia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PacienteDto {
    
    private String nombre_completo;
    private String fecha_nacimiento;
    private String no_identificacion;
    private Integer telefono;
    private String direccion;
    private String correo;
    private String poliza_seguro;
  
}
