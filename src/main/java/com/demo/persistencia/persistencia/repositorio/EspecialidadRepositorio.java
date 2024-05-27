package com.demo.persistencia.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.demo.persistencia.persistencia.Entidades.Especialidad;

public interface EspecialidadRepositorio extends CrudRepository<Especialidad, Long> {
    // Aquí se pueden agregar métodos de consulta personalizados si es necesario
}

