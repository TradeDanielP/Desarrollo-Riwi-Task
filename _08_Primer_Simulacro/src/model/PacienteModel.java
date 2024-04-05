package model;

import database.CRUD;
import database.ConfigDB;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteModel implements CRUD {


    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Paciente objPaciente = (Paciente) obj;

        try {

            String sql = "INSERT INTO paciente(nombre,apellidos,fecha_nacimiento,documento_identidad) values (?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objPaciente.getNombre());
            objPrepare.setString(2, objPaciente.getApellidos());
            objPrepare.setString(3, objPaciente.getFecha_nacimiento());
            objPrepare.setString(4, objPaciente.getDocumento_identidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objPaciente.setId_paciente(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Paciente creado correctamente");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objPaciente;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listPacientes = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM paciente;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                Paciente objPaciente = new Paciente();
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));

                listPacientes.add(objPaciente);
            }

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();

        return listPacientes;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Paciente objPaciente = (Paciente) obj;

        boolean isUpdated = false;

        try {

            String sql = "UPDATE paciente SET nombre = ?, apellidos = ?, fecha_nacimiento = ?, documento_identidad = ? WHERE id_paciente = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1,objPaciente.getNombre());
            objPrepare.setString(2,objPaciente.getApellidos());
            objPrepare.setString(3,objPaciente.getFecha_nacimiento());
            objPrepare.setString(4,objPaciente.getDocumento_identidad());
            objPrepare.setInt(5,objPaciente.getId_paciente());

            int totalRowAffected = objPrepare.executeUpdate();

            if(totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"Paciente actualizado correctamente");
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

        Paciente objPaciente = (Paciente) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDeleted = false;

        try {

            String sql = "DELETE FROM paciente WHERE id_paciente = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objPaciente.getId_paciente());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"El Paciente se elimino correctamente");
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();

        return isDeleted;
    }

    public Paciente findById(int id){

        Connection objConnection = ConfigDB.openConnection();

        Paciente objPaciente = null;

        try {

            String sql = "SELECT * FROM paciente WHERE id_paciente = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objPaciente = new Paciente();
                objPaciente.setNombre(objResult.getString("nombre"));
                objPaciente.setApellidos(objResult.getString("apellidos"));
                objPaciente.setFecha_nacimiento(objResult.getString("fecha_nacimiento"));
                objPaciente.setDocumento_identidad(objResult.getString("documento_identidad"));
                objPaciente.setId_paciente(objResult.getInt("id_paciente"));
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();

        return objPaciente;
    }

    public Paciente buscarPacientePorDocumento(String documentoBuscar){

        List<Object> listPacientes = findAll();

        for (Object paciente : listPacientes){
            Paciente pacienteFound = (Paciente) paciente;
            if(pacienteFound.getDocumento_identidad().equals(documentoBuscar)){
                return pacienteFound;
            }
        }

        return null;
    }
}
