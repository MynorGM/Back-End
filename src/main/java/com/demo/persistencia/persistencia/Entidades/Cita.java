package com.demo.persistencia.persistencia.Entidades;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "cita")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IDcita;

    @Column(name = "fec_creacion")
    private LocalDate fecCreacion;

    @ManyToOne
    @JoinColumn(name = "IDpaciente")
    private Paciente paciente;

    @Column(name = "comentario_paciente")
    private String comentarioPaciente;

    @ManyToOne
    @JoinColumn(name = "IDdoctor")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "IDespecialidad")
    private Especialidad especialidad;

    @Column(name = "fecha_cita")
    private LocalDate fechaCita;

    @Column(name = "hora_cita")
    private LocalTime horaCita;

    @Column(name = "estado")
    private String estado;

}

