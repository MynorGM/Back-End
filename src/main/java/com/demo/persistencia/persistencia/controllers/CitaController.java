package com.demo.persistencia.persistencia.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.persistencia.persistencia.Entidades.Cita;
import com.demo.persistencia.persistencia.Entidades.Doctor;
import com.demo.persistencia.persistencia.Entidades.Especialidad;
import com.demo.persistencia.persistencia.Entidades.Paciente;
import com.demo.persistencia.persistencia.dto.CitaDto;
import com.demo.persistencia.persistencia.services.CitaServicio;
import com.demo.persistencia.persistencia.services.DoctorServicio;
import com.demo.persistencia.persistencia.services.PacienteServicio;
import com.demo.persistencia.persistencia.services.EspecialidadServicio;

@RestController
@RequestMapping("/api")
public class CitaController {

    @Autowired
    private CitaServicio citaServicio;

    @Autowired
    private PacienteServicio pacienteServicio;

    @Autowired
    private DoctorServicio doctorServicio;

    @Autowired
    private EspecialidadServicio especialidadServicio;

    @GetMapping("/listarCitas")
    public ResponseEntity<List<Cita>> consultarCitas(){
        List<Cita> citas = citaServicio.consultarCitas();
        if(citas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    @PostMapping("/crearCita")
    public ResponseEntity<Cita> crearCita(@RequestBody CitaDto citaDto){
        Optional<Paciente> pacienteOptional=pacienteServicio.buscarPorId(citaDto.getIDpaciente());
        Optional<Doctor> doctorOptional= doctorServicio.buscarPorId(citaDto.getIDdoctor());
        Optional<Especialidad> especialidadOptional = especialidadServicio.buscarPorId(citaDto.getIDespecialidad());
        if(!pacienteOptional.isPresent() || !doctorOptional.isPresent() || !especialidadOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Cita cita = new Cita();
        cita.setFecCreacion(LocalDate.now());
        cita.setPaciente(pacienteOptional.get());
        cita.setComentarioPaciente(citaDto.getComentarioPaciente());
        cita.setDoctor(doctorOptional.get());
        cita.setEspecialidad(especialidadOptional.get());
        cita.setFechaCita(citaDto.getFechaCita());
        cita.setHoraCita(citaDto.getHoraCita());
        cita.setEstado(citaDto.getEstado());

        Cita citaGuardada = citaServicio.registrarCita(cita);

        return ResponseEntity.status(HttpStatus.CREATED).body(citaGuardada);

    }  

    /*aqui guarda el meetodo actualizar */

     @GetMapping("/cita/{id}")
    public ResponseEntity<?>buscarPorId(@PathVariable Long id){
        Cita cita =null;
        Map<String, Object > response = new HashMap<>();
        try{
            cita = citaServicio.findById(id);
        }catch(DataAccessException e ){
            response.put("mensaje", "Error en consulta");
            response.put("mensaje", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cita == null) {
            response.put("mensaje", "cita ID".concat(id.toString().concat("No existe cita")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            
        }
        return new ResponseEntity<Cita>(cita, HttpStatus.OK);
        
    }

    @PostMapping("/guardar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cita guardarLocalizacion(@RequestBody Cita cita){
        return citaServicio.save(cita);
    }
    

    @PutMapping("/actualizarCita/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cita> actualizarCita(@PathVariable("id") Long id, @RequestBody CitaDto citaDto){
        Optional<Cita> citaOptional = Optional.ofNullable(citaServicio.findById(id));
        if(!citaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    
        Optional<Paciente> pacienteOptional = pacienteServicio.buscarPorId(citaDto.getIDpaciente());
        Optional<Doctor> doctorOptional = doctorServicio.buscarPorId(citaDto.getIDdoctor());
        Optional<Especialidad> especialidadOptional = especialidadServicio.buscarPorId(citaDto.getIDespecialidad());
        if(!pacienteOptional.isPresent() || !doctorOptional.isPresent() || !especialidadOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    
        Cita cita = citaOptional.get();
        cita.setFecCreacion(LocalDate.now());
        cita.setPaciente(pacienteOptional.get());
        cita.setComentarioPaciente(citaDto.getComentarioPaciente());
        cita.setDoctor(doctorOptional.get());
        cita.setEspecialidad(especialidadOptional.get());
        cita.setFechaCita(citaDto.getFechaCita());
        cita.setHoraCita(citaDto.getHoraCita());
        cita.setEstado(citaDto.getEstado());
    
        Cita citaActualizada = citaServicio.save(cita);
    
        return ResponseEntity.status(HttpStatus.OK).body(citaActualizada);
    }
    

    @DeleteMapping("/api/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCita(@PathVariable Long id){
        citaServicio.delete(id);
    }
}
