package com.demo.persistencia.persistencia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.persistencia.persistencia.Entidades.Especialidad;
import com.demo.persistencia.persistencia.repositorio.EspecialidadRepositorio;

import jakarta.transaction.Transactional;

@Service
public class EspecialidadServicio {
    @Autowired
    private EspecialidadRepositorio especialidadRepositorio;

    public Optional<Especialidad> buscarPorId(Long id) {
        return especialidadRepositorio.findById(id);
    }

    public List<Especialidad> consultarEspecialidades() {
        return (List<Especialidad>) especialidadRepositorio.findAll();
    }

    public Especialidad registrarEspecialidad(Especialidad especialidad) {
        return especialidadRepositorio.save(especialidad);
    }

    @Transactional
    public Especialidad findById(Long id){
        return especialidadRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public Especialidad save(Especialidad especialidad){
        return especialidadRepositorio.save(especialidad);
    }

    @Transactional
    public void delete(Long id){
        especialidadRepositorio.deleteById(id);
     }
}