package com.demo.persistencia.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.demo.persistencia.persistencia.Entidades.Cita;

public interface CitaRepositorio extends CrudRepository<Cita, Long>{
    // Aquí se pueden agregar métodos personalizados si es necesario
}

