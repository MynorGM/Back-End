package com.demo.persistencia.persistencia.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.persistencia.persistencia.Entidades.Cita;
import com.demo.persistencia.persistencia.repositorio.CitaRepositorio;

import jakarta.transaction.Transactional;

@Service
public class CitaServicio {

    @Autowired
    private CitaRepositorio citaRepositorio;

    public List<Cita> consultarCitas() {
        return (List<Cita>) citaRepositorio.findAll();
    }

    @SuppressWarnings("null")
    public Cita registrarCita(Cita cita) {
        return citaRepositorio.save(cita);
    }

    @Transactional
    public Cita findById(Long id){
        return citaRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public Cita save(Cita cita){
        return citaRepositorio.save(cita);
    }

    @Transactional
    public void delete(Long id){
        citaRepositorio.deleteById(id);
     }
   
}

