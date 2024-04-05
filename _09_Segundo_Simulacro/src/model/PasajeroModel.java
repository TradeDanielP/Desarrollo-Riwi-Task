package model;

import database.CRUD;
import database.ConfigDB;
import entity.Pasajero;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Pasajero objPasajero = (Pasajero) obj;

        try {

            String sql = "INSERT INTO pasajero (nombre,apellido,documento_identidad) values (?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objPasajero.getNombre());
            objPrepare.setString(2, objPasajero.getApellido());
            objPrepare.setString(3, objPasajero.getDocumento_identidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objPasajero.setId_pasajero(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Pasajero creado correctamente");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objPasajero;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listPasajeros = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM pasajero;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Pasajero objPasajero = new Pasajero();
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));


                listPasajeros.add(objPasajero);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return listPasajeros;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Pasajero objPasajero = (Pasajero) obj;

        boolean isUpdated = false;

        try {

            String sql = "UPDATE pasajero SET nombre = ?, apellido = ?, documento_identidad = ? WHERE id_pasajero = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objPasajero.getNombre());
            objPrepare.setString(2, objPasajero.getApellido());
            objPrepare.setString(3, objPasajero.getDocumento_identidad());
            objPrepare.setInt(4, objPasajero.getId_pasajero());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Pasajero actualizado correctamente");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {

        Pasajero objPasajero = (Pasajero) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;

        try {

            String sql = "DELETE FROM pasajero WHERE id_pasajero = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objPasajero.getId_pasajero());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "El Pasajero se elimino correctamente");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public Pasajero findById(int id) {

        Connection objConnection = ConfigDB.openConnection();

        Pasajero objPasajero = null;

        try {

            String sql = "SELECT * FROM pasajero WHERE id_pasajero = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {
                objPasajero = new Pasajero();
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objPasajero;
    }

    public ArrayList<Object> buscarPasajeroPorNombre(String nombreBuscar){

        List<Object> listPasajeros = findAll();
        ArrayList<Object> pasajerosFound = new ArrayList<>();

        for (Object pasajero : listPasajeros){
            Pasajero pasajeroFound = (Pasajero) pasajero;
            if(pasajeroFound.getNombre().equalsIgnoreCase(nombreBuscar)){
                pasajerosFound.add(pasajeroFound);
            }
        }

        return pasajerosFound;
    }
}
