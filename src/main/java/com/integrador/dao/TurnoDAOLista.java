package com.integrador.dao;

import com.integrador.model.Turno;

import java.util.ArrayList;
import java.util.List;

public class TurnoDAOLista  implements  IDao<Turno>{

    private List<Turno> turnos = new ArrayList<>();
    @Override
    public Turno guardar(Turno turno) {
        turnos.add(turno);
        return turno;
    }

    @Override
    public Turno buscar(Integer id) {
        for (Turno turno : turnos){
            if (turno.getId().equals(id)){
                return turno;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Turno turno) {
        eliminar(turno.getId());
        guardar(turno);
    }

    @Override
    public void eliminar(Integer id) {
        Turno turnoBuscado= buscar(id);
        turnos.remove(turnoBuscado);

    }

    @Override
    public List<Turno> buscarTodos() {
        return turnos;
    }

    @Override
    public Turno buscarXString(String valor) {
        return null;
    }
}
