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

@Entity
@Table(name = "doctor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IDdoctor;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @ManyToOne
    @JoinColumn(name = "IDespecialidad", nullable = false)
    private Especialidad especialidad;

    @Column(name = "estado", nullable = false)
    private String estado;
}
