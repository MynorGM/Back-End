package com.demo.persistencia.persistencia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.persistencia.persistencia.Entidades.Paciente;
import com.demo.persistencia.persistencia.repositorio.PacienteRepositorio;

import jakarta.transaction.Transactional;

@Service
public class PacienteServicio {
    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    public List<Paciente> ConsultarPacientes(){
        return (List<Paciente>) pacienteRepositorio.findAll();
    }

    /**
     * @param paciente
     * @return
     */
  
    public Paciente registrarPaciente(Paciente paciente){
        return pacienteRepositorio.save(paciente);

    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepositorio.findById(id);
    }

     @Transactional
    public Paciente findById(Long id){
        return pacienteRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public Paciente save(Paciente paciente){
        return pacienteRepositorio.save(paciente);
    }

    @Transactional
    public void delete(Long id){
        pacienteRepositorio.deleteById(id);
     }
}
