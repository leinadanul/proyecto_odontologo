package com.integrador.controller;

import com.integrador.model.Paciente;
import com.integrador.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    /* Revisar este controller 
    
    @GetMapping
    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String email ){
        //busqueda del paciente
        Paciente paciente=pacienteService.buscarPacientePorCorreo(email);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        //devolver el nombre del template
        return "index";
    }
     */
    @GetMapping("/{id}")
    public Paciente buscarPaciente(@PathVariable Integer id){
        return pacienteService.buscarPaciente(id);
    }
    @DeleteMapping
    public RequestEntity<String> BorrarPaciebte(@PathVariable Integer id){
        pacienteService.eliminarPaciente(id);
        return null;
    }

    @PostMapping
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }
    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente) {
        //preguntar si existe el paciente
        Paciente pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if (pacienteBuscado != null) {
            pacienteService.actualizarPaciente(paciente);
            return "Se actualizó al paciente con id= " + paciente.getId();

        } else {
            return "No se pudo actualizar al paciente con id= " + paciente.getId() +
                    " ya que el mismo no existe en la base de datos. :(";
        }
    }

}
