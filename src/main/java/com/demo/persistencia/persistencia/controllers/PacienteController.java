package com.demo.persistencia.persistencia.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.persistencia.persistencia.Entidades.Paciente;
import com.demo.persistencia.persistencia.dto.PacienteDto;
import com.demo.persistencia.persistencia.services.PacienteServicio;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api")
public class PacienteController {

    @Autowired
    private PacienteServicio serviciopaciente;

   
    @GetMapping("/listarPaciente")
    public List<Paciente> consultarPaciente(){
        return serviciopaciente.ConsultarPacientes();
    }

    @PostMapping("/registrarPaciente")
    public Paciente registrarPaciente(@RequestBody PacienteDto pacienteJson) {

        Paciente paciente = new Paciente();

        paciente.setNombre_completo(pacienteJson.getNombre_completo());
        paciente.setFecha_nacimiento(pacienteJson.getFecha_nacimiento());
        paciente.setNo_identificacion(pacienteJson.getNo_identificacion());
        paciente.setTelefono(pacienteJson.getTelefono());
        paciente.setDireccion(pacienteJson.getDireccion());
        paciente.setCorreo(pacienteJson.getCorreo());
        paciente.setPoliza_seguro(pacienteJson.getPoliza_seguro());
        return serviciopaciente.registrarPaciente(paciente);
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<?>buscarPorId(@PathVariable Long id){
        Paciente paciente = null;
        Map<String, Object> response = new HashMap<>();
        try{
            paciente = serviciopaciente.findById(id);
        }catch(DataAccessException e){
            response.put("mensaje", "Error en consulta de paciente");
            response.put("mensaje", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (paciente== null) {
            response.put("mensaje", "Paciente ID".concat(id.toString().concat("No existe el paciente")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
    }

    @PostMapping("/guardarP")
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente guardarLocalizacion(@RequestBody Paciente paciente){
        return serviciopaciente.save(paciente);
    }

    @PutMapping("/paciente/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente actualizarPaciente(@RequestBody Paciente paciente, @PathVariable Long id){
       Paciente pacienteActualizado = serviciopaciente.findById(id);
       pacienteActualizado.setNombre_completo(paciente.getNombre_completo());
       pacienteActualizado.setFecha_nacimiento(paciente.getFecha_nacimiento());
       pacienteActualizado.setNo_identificacion(paciente.getNo_identificacion());
       pacienteActualizado.setTelefono(paciente.getTelefono());
       pacienteActualizado.setDireccion(paciente.getDireccion());
       pacienteActualizado.setCorreo(paciente.getCorreo());
       pacienteActualizado.setPoliza_seguro(paciente.getPoliza_seguro());
       return serviciopaciente.save(pacienteActualizado);
    }

    @DeleteMapping("/paciente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPaciente(@PathVariable Long id){
        serviciopaciente.delete(id);
    }


}
