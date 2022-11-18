package com.integrador.model;

import java.time.LocalDate;

public class Turno {
    private  Integer id;
    private Odontologo odontologo;
    private  Paciente paciente;
    private LocalDate fecha;

    public Turno(){

    }

    public Turno(Integer id, Odontologo odontologo, Paciente paciente, LocalDate fecha) {
        this.id = id;
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
