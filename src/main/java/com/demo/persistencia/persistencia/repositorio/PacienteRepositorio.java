package com.demo.persistencia.persistencia.repositorio;


import org.springframework.data.repository.CrudRepository;

import com.demo.persistencia.persistencia.Entidades.Paciente;

public interface PacienteRepositorio extends CrudRepository<Paciente, Long>{


}
