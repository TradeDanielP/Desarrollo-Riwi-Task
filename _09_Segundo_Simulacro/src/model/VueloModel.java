package model;

import database.CRUD;
import database.ConfigDB;
import entity.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VueloModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Vuelo objVuelo = (Vuelo) obj;

        try {

            String sql = "INSERT INTO vuelo (destino,fecha_salida,hora_salida,idAvion) values (?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objVuelo.getDestino());
            objPrepare.setString(2, objVuelo.getFecha_salida());
            objPrepare.setString(3, objVuelo.getHora_salida());
            objPrepare.setInt(4,objVuelo.getIdAvion());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objVuelo.setId_vuelo(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Vuelo creado correctamente");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objVuelo;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listVuelos = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM vuelo;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Vuelo objVuelo = new Vuelo();
                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setIdAvion(objResult.getInt("idAvion"));


                listVuelos.add(objVuelo);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return listVuelos;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Vuelo objVuelo = (Vuelo) obj;

        boolean isUpdated = false;

        try {

            String sql = "UPDATE vuelo SET destino = ?, fecha_salida = ?, hora_salida = ?, idAvion = ? WHERE id_vuelo = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objVuelo.getDestino());
            objPrepare.setString(2, objVuelo.getFecha_salida());
            objPrepare.setString(3, objVuelo.getHora_salida());
            objPrepare.setInt(4, objVuelo.getIdAvion());
            objPrepare.setInt(5, objVuelo.getId_vuelo());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Vuelo actualizado correctamente");
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

        Vuelo objVuelo = (Vuelo) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;

        try {

            String sql = "DELETE FROM vuelo WHERE id_vuelo = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objVuelo.getId_vuelo());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "El Vuelo se elimino correctamente");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public Vuelo findById(int id) {

        Connection objConnection = ConfigDB.openConnection();

        Vuelo objVuelo = null;

        try {

            String sql = "SELECT * FROM vuelo WHERE id_vuelo = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {
                objVuelo = new Vuelo();
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setIdAvion(objResult.getInt("idAvion"));
                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objVuelo;
    }

    public ArrayList<Object> buscarVuelosPorDestino(String buscarDestino){

        List<Object> listVuelos = findAll();

        ArrayList<Object> vuelosFound = new ArrayList<>();

        for (Object vuelo : listVuelos){
            Vuelo vueloFound = (Vuelo) vuelo;
            if (vueloFound.getDestino().equalsIgnoreCase(buscarDestino)){
                vuelosFound.add(vueloFound);
            }
        }
        return vuelosFound;
    }
}
