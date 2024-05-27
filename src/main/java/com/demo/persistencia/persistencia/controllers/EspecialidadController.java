package com.demo.persistencia.persistencia.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.demo.persistencia.persistencia.Entidades.Especialidad;
import com.demo.persistencia.persistencia.dto.EspecialidadDto;
import com.demo.persistencia.persistencia.services.EspecialidadServicio;

@RestController
@RequestMapping("/api/especialidad")
public class EspecialidadController {

    @Autowired
    private EspecialidadServicio servicioEspecialidad;

    @GetMapping("/listar")
    public List<Especialidad> consultarEspecialidades() {
        return servicioEspecialidad.consultarEspecialidades();
    }

    @PostMapping("/registrar")
    public Especialidad registrarEspecialidad(@RequestBody EspecialidadDto especialidadDto) {
        Especialidad especialidad = new Especialidad();
        // Aquí se mapearía de EspecialidadDto a la entidad Especialidad
        especialidad.setNombre(especialidadDto.getNombre());
        especialidad.setEstado(especialidadDto.getEstado());
        especialidad.setIDespecialidad(especialidadDto.getIDespecialidad());

        return servicioEspecialidad.registrarEspecialidad(especialidad);
    }

    @GetMapping("/api/especialidad/{id}")
     public ResponseEntity<?>buscarPorId(@PathVariable Long id){
        Especialidad especialidad=null;
        Map<String, Object > response = new HashMap<>();
        try{
            especialidad = servicioEspecialidad.findById(id);
        }catch(DataAccessException e ){
            response.put("mensaje", "Error en consulta");
            response.put("mensaje", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (especialidad == null) {
            response.put("mensaje", "No registro".concat(id.toString().concat("No existe en la DB")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            
        }
        return new ResponseEntity<Especialidad>(especialidad, HttpStatus.OK);
        
    }

    @PostMapping("/guardarE")
    @ResponseStatus(HttpStatus.CREATED)
    public Especialidad guardarEspecialidad(@RequestBody Especialidad especialidad){
        return servicioEspecialidad.save(especialidad);
    }

    @PutMapping("/api/especialidad/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Especialidad actualizarEspecialidad(@RequestBody Especialidad especialidad, @PathVariable Long id){
        Especialidad especialidadActualizada = servicioEspecialidad.findById(id);
        especialidadActualizada.setNombre(especialidad.getNombre());
        especialidadActualizada.setEstado(especialidad.getEstado());

        return servicioEspecialidad.save(especialidadActualizada);
    }

    @DeleteMapping("/api/especialidad/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarEspecialidad(@PathVariable Long id){
        servicioEspecialidad.delete(id);
    }
}
