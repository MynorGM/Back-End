package com.demo.persistencia.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.demo.persistencia.persistencia.Entidades.Doctor;

public interface DoctorRepositorio extends CrudRepository<Doctor, Long> {
    // Aquí se pueden agregar métodos personalizados si es necesario
}
