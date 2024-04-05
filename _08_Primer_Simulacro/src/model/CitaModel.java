package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cita;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Cita objCita = (Cita) obj;

        try {

            String sql = "INSERT INTO cita(idPaciente,idMedico,fecha_cita,hora_cita,motivo) values (?,?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objCita.getIdPaciente());
            objPrepare.setInt(2, objCita.getIdMedico());
            objPrepare.setString(3, objCita.getFecha_cita());
            objPrepare.setString(4, objCita.getHora_cita());
            objPrepare.setString(5, objCita.getMotivo());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objCita.setId_cita(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Cita Agendada correctamente");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objCita;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listCitas = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM cita;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Cita objCita = new Cita();
                objCita.setId_cita(objResult.getInt("id_cita"));
                objCita.setIdPaciente(objResult.getInt("idPaciente"));
                objCita.setIdMedico(objResult.getInt("idMedico"));
                objCita.setFecha_cita(objResult.getString("fecha_cita"));
                objCita.setHora_cita(objResult.getString("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));

                listCitas.add(objCita);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();

        return listCitas;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Cita objCita = (Cita) obj;

        boolean isUpdated = false;

        try {

            String sql = "UPDATE cita SET idPaciente = ?, idMedico = ?, fecha_cita = ?, hora_cita = ?, motivo = ? WHERE id_cita = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objCita.getIdPaciente());
            objPrepare.setInt(2,objCita.getIdMedico());
            objPrepare.setString(3,objCita.getFecha_cita());
            objPrepare.setString(4,objCita.getHora_cita());
            objPrepare.setString(5,objCita.getMotivo());
            objPrepare.setInt(6,objCita.getId_cita());

            int totalRowAffected = objPrepare.executeUpdate();

            if(totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"Cita actualizado correctamente");
            }
        }catch(Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj) {

        Cita objCita = (Cita) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;

        try {

            String sql = "DELETE FROM cita WHERE id_cita = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objCita.getId_cita());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"La Cita se elimino correctamente");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public Cita findById(int id){

        Connection objConnection = ConfigDB.openConnection();

        Cita objCita = null;

        try {

            String sql = "SELECT * FROM cita WHERE id_cita = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objCita = new Cita();
                objCita.setIdPaciente(objResult.getInt("idPaciente"));
                objCita.setIdMedico(objResult.getInt("idMedico"));
                objCita.setFecha_cita(objResult.getString("fecha_cita"));
                objCita.setHora_cita(objResult.getString("hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));
                objCita.setId_cita(objResult.getInt("id_cita"));
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();

        return objCita;
    }

    public ArrayList<Object> buscarCitaPorFecha(String fechaBuscar){

        List<Object> listCitas = findAll();

        ArrayList<Object> citasFound = new ArrayList<>();

        for(Object cita : listCitas){
            Cita found = (Cita) cita;
            if(found.getFecha_cita().equals(fechaBuscar)){
                citasFound.add(found);
            }
        }

        return citasFound;
    }

}
