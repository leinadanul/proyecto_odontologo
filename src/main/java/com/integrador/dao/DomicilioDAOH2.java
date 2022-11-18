package com.integrador.dao;

import com.integrador.model.Domicilio;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DomicilioDAOH2 implements IDao<Domicilio>{
    private static final Logger LOGGER= Logger.getLogger(DomicilioDAOH2.class);
    private static final String SQL_INSERT="INSERT INTO DOMICILIOS (CALLE," +
            "NUMERO, LOCALIDAD, PROVINCIA) VALUES (?,?,?,?)";
    private static final String SQL_SELECT="SELECT * FROM DOMICILIOS WHERE ID=?";
    private static final String SQL_UPDATE="UPDATE DOMICILIOS SET CALLE=?,NUMERO=?," +
            "LOCALIDAD=?,PROVINCIA=? WHERE ID=?";
    private static final String SQL_DELETE="DELETE FROM DOMICILIOS WHERE ID=?";
    private static final String SQL_SELECT_ALL="SELECT * FROM DOMICILIOS";
    @Override
    public Domicilio guardar(Domicilio domicilio) {
        LOGGER.info("Iniciando una operación de guardado de un domicilio");
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psInsert=connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, domicilio.getCalle());
            psInsert.setInt(2,domicilio.getNumero());
            psInsert.setString(3, domicilio.getLocalidad());
            psInsert.setString(4, domicilio.getProvincia());
            psInsert.execute();
            ResultSet rs=psInsert.getGeneratedKeys();
            while(rs.next()){
                domicilio.setId(rs.getInt(1));
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
        return domicilio;
    }

    @Override
    public Domicilio buscar(Integer id) {
        LOGGER.info("Iniciando una operación de búsqueda de un domicilio con id="+id);
        Connection connection=null;
        Domicilio domicilio=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelect=connection.prepareStatement(SQL_SELECT);
            psSelect.setInt(1,id);
            ResultSet rs= psSelect.executeQuery();
            while (rs.next()){
                domicilio= new Domicilio(rs.getInt(1),rs.getString(2),
                        rs.getInt(3),rs.getString(4),rs.getString(5));
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
        return domicilio;
    }

    @Override
    public void actualizar(Domicilio domicilio) {
        LOGGER.info("Iniciando una operación de actualización de domicilio con id="+domicilio.getId());
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psUpdate=connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1, domicilio.getCalle());
            psUpdate.setInt(2,domicilio.getNumero());
            psUpdate.setString(3,domicilio.getLocalidad());
            psUpdate.setString(4,domicilio.getProvincia());
            psUpdate.setInt(5,domicilio.getId());
            psUpdate.execute();
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
    }

    @Override
    public void eliminar(Integer id) {
        LOGGER.info("Iniciando una operación de eliminación del domicilio con id="+id);
        Connection connection=null;
        try {
            connection = BD.getConnection();
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
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Domicilio> buscarTodos() {
        LOGGER.info("Iniciando una operación de búsqueda total de los domicilios");
        Connection connection=null;
        List<Domicilio> domicilios=new ArrayList<>();
        Domicilio domicilio=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelectAll=connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs= psSelectAll.executeQuery();
            while(rs.next()){
                domicilio=new Domicilio(rs.getInt(1),rs.getString(2),
                        rs.getInt(3),rs.getString(4),rs.getString(5));
                domicilios.add(domicilio);
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
        return domicilios;

    }

    @Override
    public Domicilio buscarXString(String valor) {
        return null;
    }
}
