package com.integrador.controller;

import com.integrador.model.Odontologo;
import com.integrador.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    @GetMapping
    public String buscarOdontologo(Model model, @RequestParam ("id") Integer id){
        Odontologo odontologoBuscado=odontologoService.buscarOdontologo(id);
        model.addAttribute("nombre",odontologoBuscado.getNombre());
        model.addAttribute("apellido",odontologoBuscado.getApellido());
        // el nombre de la vista, el nombre del template formalmente
        return "busodontologo";
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
    @DeleteMapping
    public RequestEntity<String> BorrarOdontologo(@PathVariable Integer id){
        odontologoService.eliminarOdontologo(id);
        return null;
    }
}
/*    @PutMapping
    public ResponseEntity<String> actualizarTurnos(@RequestBody Odontologo odontologo){

    Odontologo BuscarOdontologo = odontologoService.buscarOdontologo(odontologo.getId());
        if (odontologo != null) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Se actualizado el Odontologo : =" +odontologo.getId());
        }
        return ResponseEntity.badRequest().build();

    }
}
*/
