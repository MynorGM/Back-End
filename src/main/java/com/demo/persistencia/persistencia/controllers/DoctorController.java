package com.demo.persistencia.persistencia.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.persistencia.persistencia.Entidades.Doctor;
import com.demo.persistencia.persistencia.Entidades.Especialidad;
import com.demo.persistencia.persistencia.dto.DoctorDto;
import com.demo.persistencia.persistencia.services.DoctorServicio;
import com.demo.persistencia.persistencia.services.EspecialidadServicio;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorServicio servicioDoctor;

    @Autowired
    private EspecialidadServicio especialidadServicio;

    @GetMapping("/listar")
    public List<Doctor> consultarDoctores() {
        return servicioDoctor.consultarDoctores();
    }

    @PostMapping("/registrar")
    public ResponseEntity<Doctor> crearDoctor(@RequestBody DoctorDto doctorDto){
        Optional<Especialidad> especialidadOptional=Optional.empty();
        if (!especialidadOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Doctor doctor = new Doctor();
        doctor.setNombreCompleto(doctorDto.getNombreCompleto());
        doctor.setEspecialidad(especialidadOptional.get());
        doctor.setEstado(doctorDto.getEstado());

        Doctor doctorGuardado = servicioDoctor.registrarDoctor(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorGuardado);
    }

    @PostMapping("/guardarD")
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor guardaDoctor(@RequestBody Doctor doctor){
        return servicioDoctor.save(doctor);
    }

    @GetMapping("/api/doctor/{id}")
    public ResponseEntity<?>buscarPorId(@PathVariable Long id){
        Doctor doctor=null;
        Map<String, Object> response = new HashMap<>();
        try{
            doctor = servicioDoctor.findById(id);
        }catch(DataAccessException e){
            response.put("mensaje", "Error en consulta");
            response.put("mensaje", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (doctor == null) {
            response.put("mensaje", "Doctor ID".concat(id.toString().concat("No existe")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
    }

@PutMapping("/api/doctor/{id}")
@ResponseStatus(HttpStatus.CREATED)
public ResponseEntity<Doctor> actualizarDoctor(@PathVariable("id") Long id, @RequestBody DoctorDto doctorDto){
    Optional<Doctor> doctorOptional = Optional.ofNullable(servicioDoctor.findById(id));
    if (!doctorOptional.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    Optional<Especialidad> especialidadOptional = especialidadServicio.buscarPorId(doctorDto.getIDespecialidad());
    if (!especialidadOptional.isPresent()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    Doctor doctor = doctorOptional.get();
    doctor.setNombreCompleto(doctorDto.getNombreCompleto());
    doctor.setEspecialidad(especialidadOptional.get());
    doctor.setEstado(doctorDto.getEstado());

    Doctor doctorActualizado = servicioDoctor.save(doctor);

    return ResponseEntity.status(HttpStatus.OK).body(doctorActualizado);
}

@DeleteMapping("/api/doctor/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void eliminarDoctor(@PathVariable Long id){
    servicioDoctor.delete(id);
}

}


