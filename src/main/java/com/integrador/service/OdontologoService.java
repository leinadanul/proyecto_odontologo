package com.integrador.service;

import com.integrador.dao.IDao;
import com.integrador.dao.OdontologoDAOH2;
import com.integrador.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(){
        odontologoIDao= new OdontologoDAOH2();
    }
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoIDao.guardar(odontologo);
    }
    public Odontologo buscarOdontologo(Integer id){
        return odontologoIDao.buscar(id);
    }
    public void actualizarOdontologo(Odontologo odontologo){
        odontologoIDao.actualizar(odontologo);
    }
    public void eliminarOdontologo(Integer id){
        odontologoIDao.eliminar(id);
    }
    public List<Odontologo> buscarTodosOdontologos(){
        return odontologoIDao.buscarTodos();
    }
/*
    T  guardar (T t);
    T buscar (Integer id);
    void actualizar(T t);
    void eliminar (Integer id);
    List<T> buscarTodos();
    T buscarXString(String valor);

 */
}
