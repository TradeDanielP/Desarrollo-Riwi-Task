package controller;

import entity.Medico;
import entity.Paciente;
import model.MedicoModel;
import model.PacienteModel;

import javax.swing.*;

public class PacienteController {


    public static void create() {

        PacienteModel objPacienteModel = new PacienteModel();

        String nombrePaciente = JOptionPane.showInputDialog("Ingresa los nombres del Paciente");
        String apellidosPaciente = JOptionPane.showInputDialog("Ingresa los apellidos del Paciente");
        String fechaNacimientoPaciente = JOptionPane.showInputDialog("Ingresa la fecha de nacimiento del paciente \n En este formato YYYY-MM-DD");
        String documentoIdentificacionPaciente = JOptionPane.showInputDialog("Ingresa el documento de identidad del paciente");

        Paciente objPaciente = new Paciente();
        objPaciente.setNombre(nombrePaciente);
        objPaciente.setApellidos(apellidosPaciente);
        objPaciente.setFecha_nacimiento(fechaNacimientoPaciente);
        objPaciente.setDocumento_identidad(documentoIdentificacionPaciente);

        objPaciente = (Paciente) objPacienteModel.insert(objPaciente);

        JOptionPane.showMessageDialog(null, objPaciente.toString());

    }

    public static void getAll() {

        PacienteModel objModel = new PacienteModel();

        String listPacientes = "LISTA DE PACIENTES \n";

        for (Object paciente : objModel.findAll()) {
            Paciente objPaciente = (Paciente) paciente;
            listPacientes += objPaciente.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listPacientes);
    }

    public static String getAllString() {

        PacienteModel objModel = new PacienteModel();

        String listPacientes = "LISTA DE PACIENTES \n";

        for (Object paciente : objModel.findAll()) {
            Paciente objPaciente = (Paciente) paciente;
            listPacientes += objPaciente.toString() + "\n";
        }

        return listPacientes;
    }

    public static void update() {

        PacienteModel objPacienteModel = new PacienteModel();

        String listPacientes = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listPacientes + "\nIngresa el ID del Medico que desea editar"));

        Paciente objPaciente = objPacienteModel.findById(idUpdate);

        if (objPaciente == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el Paciente");
        } else {

            String nombre = JOptionPane.showInputDialog(null, "Modifica los nombres del paciente", objPaciente.getNombre());
            String apellidos = JOptionPane.showInputDialog(null, "Modifica los apellidos del Paciente", objPaciente.getApellidos());
            String fechaNacimiento = JOptionPane.showInputDialog(null, "Modifica la fecha de nacimiento del paciente", objPaciente.getFecha_nacimiento());
            String documentoIdentidad = JOptionPane.showInputDialog(null, "Modifica el numero de identidad del paciente", objPaciente.getDocumento_identidad());

            objPaciente.setNombre(nombre);
            objPaciente.setApellidos(apellidos);
            objPaciente.setFecha_nacimiento(fechaNacimiento);
            objPaciente.setDocumento_identidad(documentoIdentidad);

            objPacienteModel.update(objPaciente);
        }
    }


    public static void delete(){

        PacienteModel objModel = new PacienteModel();

        String listPacientes = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPacientes+"\n Ingresa el id del paciente que desea eliminar"));

        Paciente objPaciente = objModel.findById(idDelete);

        int confirm = 1;

        if (objPaciente == null){
            JOptionPane.showMessageDialog(null,"No se encontro el id del Paciente");
        } else {
            confirm = JOptionPane.showConfirmDialog(null,"¿Estas seguro que desea eliminar este Paciente?\nEsta opcion no se puede deshacer");
            if (confirm == 0 ) objModel.delete(objPaciente);
        }
    }

    public static void buscarPorDocumento(){
        PacienteModel objModel = new PacienteModel();

        String documentoBuscar = JOptionPane.showInputDialog("Digite el Documento por el que desea buscar");

        if (objModel.buscarPacientePorDocumento(documentoBuscar) == null){
            JOptionPane.showMessageDialog(null,"No se encontro ningun Paciente registrado con este documento");
        }else {
            JOptionPane.showMessageDialog(null,objModel.buscarPacientePorDocumento(documentoBuscar).toString());
        }
    }


}
