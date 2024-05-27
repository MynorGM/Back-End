package com.demo.persistencia.persistencia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.persistencia.persistencia.Entidades.Doctor;
import com.demo.persistencia.persistencia.repositorio.DoctorRepositorio;

import jakarta.transaction.Transactional;

@Service
public class DoctorServicio {
    @Autowired
    private DoctorRepositorio doctorRepositorio;

    public List<Doctor> consultarDoctores() {
        return (List<Doctor>) doctorRepositorio.findAll();
    }

    @SuppressWarnings("null")
    public Doctor registrarDoctor(Doctor doctor) {
        return doctorRepositorio.save(doctor);
    }

    @SuppressWarnings("null")
    public Optional<Doctor> buscarPorId(Long id) {
        return doctorRepositorio.findById(id);
    }

     @Transactional
    public Doctor findById(Long id){
        return doctorRepositorio.findById(id).orElse(null);
    }

    @Transactional
    public Doctor save(Doctor doctor){
        return doctorRepositorio.save(doctor);
    }

    @Transactional
    public void delete(Long id){
        doctorRepositorio.deleteById(id);
     }

}

