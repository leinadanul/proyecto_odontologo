package com.integrador.controller;

import com.integrador.model.Turno;
import com.integrador.service.OdontologoService;
import com.integrador.service.PacienteService;
import com.integrador.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTurnos() {
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) {
        //si el odontologo o paciente no existe error
        OdontologoService odontologoService = new OdontologoService();
        PacienteService pacienteService = new PacienteService();
        if (odontologoService.buscarOdontologo(turno.getOdontologo().getId()) != null
                && pacienteService.buscarPaciente(turno.getPaciente().getId()) != null) {
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/{id}")
    public RequestEntity<String> BorrarTurno(@PathVariable Integer id) {
        turnoService.eliminarTurno(id);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id) {
        Turno turnoBuscado = turnoService.buscarTurno(id);
        if (turnoBuscado != null) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se elimino el turno con id=" + id);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarTurno(@PathVariable Integer id) {
        Turno turnoBuscacdo = turnoService.buscarTurno(id);
        if (turnoBuscacdo != null) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Se elimino Turno" + id);
        } else {
            return ResponseEntity.badRequest().body("No se puede eliminar el turno " + "con id = " + id);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable Integer id) {
        Turno turnoBuscado = turnoService.buscarTurno(id);
        if (turnoBuscado != null) {
            //el turno existe
            return ResponseEntity.ok(turnoBuscado);
        } else {
            //no existe turno con id
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurnos(@RequestBody Turno turno) {
        //preguntar si existe el turno
        OdontologoService odontologoService = new OdontologoService();
        PacienteService pacienteService = new PacienteService();
        if (odontologoService.buscarOdontologo(turno.getOdontologo().getId()) != null
                && pacienteService.buscarPaciente(turno.getPaciente().getId()) != null) {
            Turno turnoBuscado = turnoService.buscarTurno(turno.getId());
            if (turnoBuscado != null) {
                turnoService.actualizarTurno(turno);
                return ResponseEntity.ok("Se actualizado el turno con id=" + turno.getId());
            }

        }
        return ResponseEntity.badRequest().build();

    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) {
        Turno turnoBuscado = turnoService.buscarTurno(turno.getId());
        if (turnoBuscado != null) {
            //el turno existe veridicar el repo
            turnoService.actualizarTurno(turno);
        } else {
            //no existe el turno
            return ResponseEntity.badRequest().body("no encontramos el turno que se quiere modificar");

        }
        return null;
    }
}