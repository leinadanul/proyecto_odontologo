package com.integrador.dao;

import com.integrador.model.Domicilio;
import com.integrador.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOH2 implements IDao<Paciente> {
    private static final Logger LOGGER= Logger.getLogger(PacienteDAOH2.class);
    private static final String SQL_SELECT_ALL="SELECT * FROM PACIENTES";
    private static final String SQL_SELECT_ONE="SELECT * FROM PACIENTES WHERE ID=?";
    private static final String SQL_SELECT_BY_EMAIL="SELECT * FROM PACIENTES WHERE EMAIL=?";
    private static final String SQL_INSERT="INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA," +
            "FECHA_INGRESO,DOMICILIO_ID,EMAIL) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE="UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?, CEDULA=?," +
            "FECHA_INGRESO=?, DOMICILIO_ID=?, EMAIL=? WHERE ID=?";
    @Override
    public Paciente guardar(Paciente paciente) {
        LOGGER.info("Iniciando el guardado del paciente con nombre="+paciente.getNombre());
        Connection connection=null;
        try{
            connection=BD.getConnection();
            DomicilioDAOH2 daoAux=new DomicilioDAOH2();
            Domicilio nuevoDomicilio=daoAux.guardar(paciente.getDomicilio());
            PreparedStatement psInsert=connection.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getCedula());
            psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            //sacamos el id que generó el DAO de domicilio y lo colocamos como si fuera
            //una clave foranea
            psInsert.setInt(5,nuevoDomicilio.getId());
            psInsert.setString(6, paciente.getEmail());
            psInsert.execute();
            ResultSet clave=psInsert.getGeneratedKeys();
            while (clave.next()){
                paciente.setId(clave.getInt(1));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public Paciente buscar(Integer id) {
        LOGGER.info("Iniciando la búsqueda del paciente con id="+id);
        Connection connection=null;
        Paciente paciente=null;
        Domicilio domicilio=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelectOne=connection.prepareStatement(SQL_SELECT_ONE);
            psSelectOne.setInt(1,id);
            ResultSet rs=psSelectOne.executeQuery();
            DomicilioDAOH2 daoAux=new DomicilioDAOH2();
            while (rs.next()){
                //que tendria un rs común
                //(51,'Ezequiel','Baspineiro',871,'2022-11-2',2) <--- el último es FK
                domicilio=daoAux.buscar(rs.getInt(6));
                paciente=new Paciente(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),
                        rs.getDate(5).toLocalDate(),domicilio,rs.getString(7));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return paciente;
    }
    @Override
    public void actualizar(Paciente paciente) {
        LOGGER.info("Iniciando la actualización del paciente con id="+paciente.getId());
        Connection connection=null;
        try{
            connection=BD.getConnection();
            DomicilioDAOH2 daoAux=new DomicilioDAOH2();
            daoAux.actualizar(paciente.getDomicilio());
            PreparedStatement psUpdate=connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1, paciente.getNombre());
            psUpdate.setString(2, paciente.getApellido());
            psUpdate.setString(3, paciente.getCedula());
            psUpdate.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            //sacamos el id que generó el DAO de domicilio y lo colocamos como si fuera
            //una clave foranea
            psUpdate.setInt(5,paciente.getDomicilio().getId());
            psUpdate.setString(6, paciente.getEmail());
            psUpdate.setInt(7,paciente.getId());
            psUpdate.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        LOGGER.info("Iniciando la eliminación del paciente con id="+id);
    }

    @Override
    public List<Paciente> buscarTodos() {
        LOGGER.info("Iniciando la búsqueda de todos los pacientes");
        Connection connection=null;
        List<Paciente> pacientes=new ArrayList<>();
        Paciente paciente=null;
        Domicilio domicilio=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelectAll=connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs=psSelectAll.executeQuery();
            DomicilioDAOH2 daoAux=new DomicilioDAOH2();
            while (rs.next()){
                //que tendria un rs común
                //(51,'Ezequiel','Baspineiro',871,'2022-11-2',2) <--- el último es FK
                domicilio=daoAux.buscar(rs.getInt(6));
                paciente=new Paciente(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),
                        rs.getDate(5).toLocalDate(),domicilio,rs.getString(7));
                pacientes.add(paciente);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return pacientes;
    }
    //busca al paciente por correo
    @Override
    public Paciente buscarXString(String valor) {
        LOGGER.info("Iniciando la búsqueda del paciente con email="+valor);
        Connection connection=null;
        Paciente paciente=null;
        Domicilio domicilio=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelectOne=connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            psSelectOne.setString(1,valor);
            ResultSet rs=psSelectOne.executeQuery();
            DomicilioDAOH2 daoAux=new DomicilioDAOH2();
            while (rs.next()){
                //que tendria un rs común
                //(51,'Ezequiel','Baspineiro',871,'2022-11-2',2) <--- el último es FK
                domicilio=daoAux.buscar(rs.getInt(6));
                paciente=new Paciente(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4),
                        rs.getDate(5).toLocalDate(),domicilio,rs.getString(7));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return paciente;
    }
}
