package controller;

import entity.Medico;
import model.MedicoModel;

import javax.swing.*;
import java.util.List;

public class MedicoController {

    public static void create() {

        MedicoModel objMedicoModel = new MedicoModel();

        String listEspecialidades = EspecialidadController.getAllString();

        String nombreMedico = JOptionPane.showInputDialog("Ingresa los nombres del medico");
        String apellidosMedico = JOptionPane.showInputDialog("Ingresa los apellidos del medico");
        int especialidadMedico = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidades + "\n Ingrese el ID de la especialidad del medico"));

        Medico objMedico = new Medico();
        objMedico.setNombre(nombreMedico);
        objMedico.setApellidos(apellidosMedico);
        objMedico.setIdEspecialidad(especialidadMedico);

        objMedico = (Medico) objMedicoModel.insert(objMedico);

        JOptionPane.showMessageDialog(null, objMedico.toString());

    }

    public static List<Object> getAll() {

        MedicoModel objModel = new MedicoModel();

        String listMedicos = "LISTA DE MEDICOS \n";

        for (Object medico : objModel.findAll()) {
            Medico objMedico = (Medico) medico;
            listMedicos += objMedico.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listMedicos);
        return null;
    }

    public static String getAllString() {

        MedicoModel objModel = new MedicoModel();

        String listMedicos = "LISTA DE MEDICOS \n";

        for (Object medico : objModel.findAll()) {
            Medico objMedico = (Medico) medico;
            listMedicos += objMedico.toString() + "\n";
        }

        return listMedicos;
    }

    public static void update() {

        MedicoModel objMedicoModel = new MedicoModel();

        String listMedico = getAllString();

        String listEspecialidades = EspecialidadController.getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listMedico + "\nIngresa el ID del Medico que desea editar"));

        Medico objMedico = objMedicoModel.findById(idUpdate);

        if (objMedico == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el medico");
        } else {

            String nombre = JOptionPane.showInputDialog(null, "Modifica los nombres del Medico", objMedico.getNombre());
            String apellidos = JOptionPane.showInputDialog(null, "Modifica los apellidos del Medico", objMedico.getApellidos());
            int idEspecialidad = Integer.parseInt(JOptionPane.showInputDialog(null, listEspecialidades + "\n Ingresa el ID de la nueva Especialidad del medico", objMedico.getIdEspecialidad()));

            objMedico.setNombre(nombre);
            objMedico.setApellidos(apellidos);
            objMedico.setIdEspecialidad(idEspecialidad);

            objMedicoModel.update(objMedico);
        }
    }


    public static void delete(){

        MedicoModel objModel = new MedicoModel();

        String listMedicos = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listMedicos+"\n Ingresa el id del medico que desea eliminar"));

        Medico objMedico = objModel.findById(idDelete);

        int confirm = 1;

        if (objMedico == null){
            JOptionPane.showMessageDialog(null,"No se encontro el id del Medico");
        } else {
            confirm = JOptionPane.showConfirmDialog(null,"¿Estas seguro que desea eliminar este Medico?\nEsta opcion no se puede deshacer");
            if (confirm == 0 ) objModel.delete(objMedico);
        }

    }


}
