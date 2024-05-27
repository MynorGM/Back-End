package com.demo.persistencia.persistencia.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "especialidad", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IDespecialidad;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private String estado;
}

