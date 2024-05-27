package com.demo.persistencia.persistencia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class CitaDto {
    
    private long IDpaciente;
    private long IDdoctor;
    private long IDespecialidad;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private String comentarioPaciente;
    private String estado;

    
}
