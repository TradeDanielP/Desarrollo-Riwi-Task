package model;

import database.CRUD;
import database.ConfigDB;
import entity.Reservacion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservacionModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Reservacion objReservacion = (Reservacion) obj;

        try {
            String sql = "INSERT INTO reservacion (idPasajero,idVuelo,fecha_reservacion,asiento) values (?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objReservacion.getIdPasajero());
            objPrepare.setInt(2, objReservacion.getIdVuelo());
            objPrepare.setString(3, objReservacion.getFecha_reservacion());
            objPrepare.setString(4,objReservacion.getAsiento());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objReservacion.setId_reservacion(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Reservacion creada correctamente");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objReservacion;
    }

    public int count(int idVuelo){

        Connection objConnection = ConfigDB.openConnection();

        int count = 0;

        try {
            String sqlCount = "SELECT COUNT(*) as nombre from reservacion where idVuelo = ?;";

            PreparedStatement objPrepareCount = objConnection.prepareStatement(sqlCount);

            objPrepareCount.setInt(1,idVuelo);

            ResultSet objResultCount = objPrepareCount.executeQuery();
            if (objResultCount.next()){
                count = objResultCount.getInt("nombre");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        return count;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listReservaciones = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM reservacion;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Reservacion objReservacion = new Reservacion();
                objReservacion.setId_reservacion(objResult.getInt("id_reservacion"));
                objReservacion.setIdPasajero(objResult.getInt("idPasajero"));
                objReservacion.setIdVuelo(objResult.getInt("idVuelo"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));


                listReservaciones.add(objReservacion);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return listReservaciones;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Reservacion objReservacion = (Reservacion) obj;

        boolean isUpdated = false;

        try {

            String sql = "UPDATE reservacion SET idPasajero = ?, idVuelo = ?, fecha_reservacion = ?, asiento = ? WHERE id_reservacion = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objReservacion.getIdPasajero());
            objPrepare.setInt(2, objReservacion.getIdVuelo());
            objPrepare.setString(3, objReservacion.getFecha_reservacion());
            objPrepare.setString(4, objReservacion.getAsiento());
            objPrepare.setInt(5, objReservacion.getId_reservacion());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Reservacion actualizada correctamente");
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

        Reservacion objReservacion = (Reservacion) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;

        try {

            String sql = "DELETE FROM reservacion WHERE id_reservacion = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, objReservacion.getId_reservacion());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "La Reservacion se elimino correctamente");
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public Reservacion findById(int id) {

        Connection objConnection = ConfigDB.openConnection();

        Reservacion objReservacion = null;

        try {

            String sql = "SELECT * FROM reservacion WHERE id_reservacion = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {
                objReservacion = new Reservacion();
                objReservacion.setIdPasajero(objResult.getInt("idPasajero"));
                objReservacion.setIdVuelo(objResult.getInt("idVuelo"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));
                objReservacion.setId_reservacion(objResult.getInt("id_reservacion"));
            }
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objReservacion;
    }

    public ArrayList<Object> buscarReservacionesPorVuelo(int idVuelo){

        List<Object> listReservaciones = findAll();

        VueloModel objVueloModel = new VueloModel();
        ArrayList<Object> listVuelos = (ArrayList<Object>) objVueloModel.findAll();

        ArrayList<Object> reservasFound = new ArrayList<>();

        for(Object vuelo : listVuelos){
            Vuelo vueloFound = (Vuelo) vuelo;
            if(vueloFound.getId_vuelo() == idVuelo){
                for (Object reserva : listReservaciones){
                    Reservacion reservaFound = (Reservacion) reserva;
                    if (reservaFound.getIdVuelo() == idVuelo){
                        reservasFound.add(reservaFound);
                    }
                }
            }
        }
        return reservasFound;
    }

    public ArrayList<Object> buscarAsientosPorReserva(int idVuelo){

        List<Object> listReservaciones = findAll();

        VueloModel objVueloModel = new VueloModel();
        ArrayList<Object> listVuelos = (ArrayList<Object>) objVueloModel.findAll();

        ArrayList<Object> asientosFound = new ArrayList<>();

        for (Object vuelo : listVuelos){
            Vuelo vueloFound = (Vuelo) vuelo;
            if (vueloFound.getId_vuelo() == idVuelo){
                for (Object asiento : listReservaciones){
                    Reservacion asientoFound = (Reservacion) asiento;
                    if(asientoFound.getIdVuelo() == idVuelo){
                        String asientoFounded = asientoFound.getAsiento();
                        asientosFound.add(asientoFounded);
                    }
                }
            }
        }
        return asientosFound;
    }
}
