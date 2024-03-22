package model;

import database.CRUD;
import database.ConfigDB;
import entity.Autor;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorModel implements CRUD {

    public Object insert(Object obj){

        Connection objConnection = ConfigDB.openConnection();

        Autor objAutor = (Autor) obj;

        try {

            String sql = "INSERT INTO autor(nombre,nacionalidad) VALUES (?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objAutor.getName());
            objPrepare.setString(2,objAutor.getNationality());

            objPrepare.execute();

            ResultSet objRest = objPrepare.getGeneratedKeys();


            while (objRest.next()){
                objAutor.setId(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Autor ingresado satisfactoriamente");

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();

        return objAutor;

    }

    @Override
    public List<Object> findAll() {

        List<Object> listAutores = new ArrayList<>();


        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM autor;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Autor objAutor = new Autor();
                objAutor.setId(objResult.getInt("id"));
                objAutor.setName(objResult.getString("nombre"));
                objAutor.setNationality(objResult.getString("nacionalidad"));

                listAutores.add(objAutor);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();
        return listAutores;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();

        Autor objAutor = (Autor) obj;

        boolean isUpdated = false;

        try {
            String sql = "UPDATE autor SET nombre = ?, nacionalidad = ? WHERE id = ?;";

            PreparedStatement objPrepare =  objConnection.prepareStatement(sql);

            objPrepare.setString(1,objAutor.getName());
            objPrepare.setString(2,objAutor.getNationality());
            objPrepare.setInt(3,objAutor.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if(totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"El Autor se actualizo correctamente");
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }

        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {

        Autor objAutor = (Autor) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDelete = false;

        try {
            String sql = "DELETE FROM autor WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objAutor.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"El Autor se elimino correctamente");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();

        return isDelete;
    }

    public Autor findById(int id){

        Connection objConnection = ConfigDB.openConnection();


        Autor objAutor = null;

        try {

            String sql = "SELECT * FROM autor WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()){
                objAutor = new Autor();
                objAutor.setName(objResult.getString("nombre"));
                objAutor.setNationality(objResult.getString("nacionalidad"));
                objAutor.setId(objResult.getInt("id"));
            }

        }catch(Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();

        return objAutor;
    }


}
