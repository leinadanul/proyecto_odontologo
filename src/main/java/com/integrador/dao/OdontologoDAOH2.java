package com.integrador.dao;

import com.integrador.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDao<Odontologo> {
    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS (MATRICULA," +
            "NOMBRE,APELLIDO) VALUES (?,?,?)";
    private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SELECT_ONE="SELECT * FROM ODONTOLOGOS " +
            "WHERE ID=?";
    private static final String SQL_DELETE="DELETE FROM ODONTOLOGOS WHERE " +
            "ID=?";
    private static final String SQL_UPDATE="UPDATE ODONTOLOGOS SET " +
            "MATRICULA=?, NOMBRE=?, APELLIDO=? WHERE ID=?";
    private static final Logger LOGGER=Logger.getLogger(OdontologoDAOH2.class);
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Iniciando la operación de guardado del odontologo con nombre: "+
                odontologo.getNombre());
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psInsert=connection.prepareStatement(SQL_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1,odontologo.getMatricula());
            psInsert.setString(2,odontologo.getNombre());
            psInsert.setString(3,odontologo.getApellido());
            psInsert.execute();
            ResultSet clave=psInsert.getGeneratedKeys();
            while (clave.next()){
                odontologo.setId(clave.getInt(1));
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
        return odontologo;
    }

    @Override
    public Odontologo buscar(Integer id) {
        LOGGER.info("Iniciando la busqueda del odontologo con id= "+id);
        Connection connection=null;
        Odontologo odontologo=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelect=connection.prepareStatement(SQL_SELECT_ONE);
            psSelect.setInt(1,id);
            ResultSet rs=psSelect.executeQuery();
            while (rs.next()){
                odontologo=new Odontologo(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4));
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
        return odontologo;
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        LOGGER.info("Iniciando la operación de actualización del odontologo con nombre: "+
                odontologo.getNombre());
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psUpdate=connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1,odontologo.getMatricula());
            psUpdate.setString(2,odontologo.getNombre());
            psUpdate.setString(3,odontologo.getApellido());
            psUpdate.setInt(4,odontologo.getId());
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
        LOGGER.warn("Iniciando la eliminación del odontologo con id= "+id);
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psDelete=connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1,id);
            psDelete.execute();
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
    public List<Odontologo> buscarTodos() {
        LOGGER.info("Iniciando la busqueda de todos los odontologos de la base de datos");
        Connection connection=null;
        List<Odontologo> listaOdontologos= new ArrayList<>();
        Odontologo odontologo=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelect=connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs=psSelect.executeQuery();
            while (rs.next()){
                odontologo=new Odontologo(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4));
                listaOdontologos.add(odontologo);
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
        return listaOdontologos;
    }

    @Override
    public Odontologo buscarXString(String valor) {
        return null;
    }
}
