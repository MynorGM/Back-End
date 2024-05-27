package com.demo.persistencia.persistencia.Entidades;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @Column(name ="IDpaciente" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long IDpaciente;
    
    @NotEmpty(message = "Campo no puede quedar vacio")
    private String nombre_completo;

    private String fecha_nacimiento;

    @Pattern(regexp = "\\d{13}", message = "Numero de DPI invalido")
    private String no_identificacion;

    @Pattern(regexp = "\\d{8}", message = "Numero de telefono invalido")
    private Integer telefono;

    private String direccion;
    
    @Email(message = "Formato de correo invalido")
    private String correo;
    
    private String poliza_seguro;

    public Paciente() {
    }

    public Paciente(long iDpaciente, @NotEmpty(message = "Campo no puede quedar vacio") String nombre_completo,
            String fecha_nacimiento,
            @Pattern(regexp = "\\d{13}", message = "Numero de DPI invalido") String no_identificacion,
            @Pattern(regexp = "\\d{8}", message = "Numero de telefono invalido") Integer telefono, String direccion,
            @Email(message = "Formato de correo invalido") String correo, String poliza_seguro) {
        IDpaciente = iDpaciente;
        this.nombre_completo = nombre_completo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.no_identificacion = no_identificacion;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.poliza_seguro = poliza_seguro;
    }

    public long getIDpaciente() {
        return IDpaciente;
    }

    public void setIDpaciente(long iDpaciente) {
        IDpaciente = iDpaciente;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNo_identificacion() {
        return no_identificacion;
    }

    public void setNo_identificacion(String no_identificacion) {
        this.no_identificacion = no_identificacion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPoliza_seguro() {
        return poliza_seguro;
    }

    public void setPoliza_seguro(String poliza_seguro) {
        this.poliza_seguro = poliza_seguro;
    }
  
    

}
