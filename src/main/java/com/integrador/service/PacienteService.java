package com.integrador.service;

import com.integrador.dao.IDao;
import com.integrador.dao.PacienteDAOH2;
import com.integrador.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteService {
    private IDao<Paciente> pacienteIDao;

    public PacienteService() {
        this.pacienteIDao = new PacienteDAOH2();
    }

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteIDao.guardar(paciente);
    }
    public Paciente buscarPaciente(Integer id){
        return pacienteIDao.buscar(id);
    }
    public void eliminarPaciente(Integer id){
        pacienteIDao.eliminar(id);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteIDao.actualizar(paciente);
    }
    public List<Paciente> buscarPacientes(){
        return pacienteIDao.buscarTodos();
    }
    public Paciente buscarPacientePorCorreo(String correo){
        return pacienteIDao.buscarXString(correo);
    }
}
