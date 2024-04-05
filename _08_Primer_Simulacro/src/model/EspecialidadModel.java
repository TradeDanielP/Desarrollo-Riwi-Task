package model;

import controller.MedicoController;
import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;
import entity.Medico;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadModel implements CRUD {

    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Especialidad objEspecialidad = (Especialidad) obj;

        try {

            String sql = "INSERT INTO especialidad(nombre,descripcion) values (?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objEspecialidad.getNombre());
            objPrepare.setString(2, objEspecialidad.getDescripcion());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objEspecialidad.setId_especialidad(objResult.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Especialidad Creada Satisfactoriamente");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return objEspecialidad;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listEspecialidades = new ArrayList<>();

        Connection objConnection = ConfigDB.openConnection();

        try {

            String sql = "SELECT * FROM especialidad;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Especialidad objEspecialidad = new Especialidad();
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));

                listEspecialidades.add(objEspecialidad);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();

        return listEspecialidades;
    }

    @Override
    public boolean update(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Especialidad objEspecialidad = (Especialidad) obj;

        boolean isUpdated = false;

        try {

            String sql = "UPDATE especialidad SET nombre = ?, descripcion = ? WHERE id_especialidad = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objEspecialidad.getNombre());
            objPrepare.setString(2, objEspecialidad.getDescripcion());
            objPrepare.setInt(3, objEspecialidad.getId_especialidad());

            int totalRowAffected = objPrepare.executeUpdate();

            if (totalRowAffected > 0) {
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "La Especialidad se actualizo correctamente");
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

        Especialidad objEspecialidad = (Especialidad) obj;

        Connection objConnection = ConfigDB.openConnection();

        boolean isDelete = false;

        try {

            String sql = "DELETE FROM especialidad WHERE id_especialidad = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,objEspecialidad.getId_especialidad());

            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0){
                isDelete = true;
                JOptionPane.showMessageDialog(null,"La especialidad se elimino correctamente");
            }

        }catch (Exception error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }
        ConfigDB.closeConnection();

        return isDelete;
    }

    public Especialidad findById(int id) {

        Connection objConnection = ConfigDB.openConnection();

        Especialidad objEspecialidad = null;

        try {

            String sql = "SELECT * FROM especialidad WHERE id_especialidad = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {
                objEspecialidad = new Especialidad();
                objEspecialidad.setNombre(objResult.getString("nombre"));
                objEspecialidad.setDescripcion(objResult.getString("descripcion"));
                objEspecialidad.setId_especialidad(objResult.getInt("id_especialidad"));
            }

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return objEspecialidad;
    }

    public ArrayList<Object> buscarMedicosPorEspecialidad(int idEspecialidad){

        List<Object> listEspecialidades = findAll();

        MedicoModel objMedicoModel = new MedicoModel();
        ArrayList<Object> listMedicos = (ArrayList<Object>) objMedicoModel.findAll();

        ArrayList<Object> medicosFound = new ArrayList<>();

        for(Object especialidad : listEspecialidades){
            Especialidad especialidadFound = (Especialidad) especialidad;
            if(especialidadFound.getId_especialidad() == idEspecialidad){
                for (Object medico : listMedicos){
                    Medico medicoFound = (Medico) medico;
                    if (medicoFound.getIdEspecialidad() == idEspecialidad){
                        medicosFound.add(medicoFound);
                    }
                }
            }
        }

        return medicosFound;
    }

}

