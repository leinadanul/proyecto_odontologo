package com.integrador.service;

import com.integrador.dao.IDao;
import com.integrador.dao.TurnoDAOLista;
import com.integrador.model.Turno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    private IDao<Turno> turnoIDao;
    public TurnoService(){
        turnoIDao = new  TurnoDAOLista();
    }
    public List<Turno> listarTurnos(){
        return turnoIDao.buscarTodos();
    }
    public Turno buscarTurno(Integer id){
        return  turnoIDao.buscar(id);
    }
    public void eliminarTurno(Integer id){
        turnoIDao.eliminar(id);
    }
    public void actualizarTurno(Turno turno){
        turnoIDao.actualizar(turno);
    }
    public Turno guardarTurno(Turno turno){
        return turnoIDao.guardar(turno);
    }
}
