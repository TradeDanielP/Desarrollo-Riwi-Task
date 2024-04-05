package controller;

import entity.Cita;
import entity.Paciente;
import model.CitaModel;
import model.PacienteModel;

import javax.swing.*;

public class CitaController {


    public static void assign() {

        CitaModel objCitaModel = new CitaModel();

        String listPacientes = PacienteController.getAllString();
        String listMedicos = MedicoController.getAllString();

        int idPacienteCita = Integer.parseInt(JOptionPane.showInputDialog(listPacientes + "\nIngresa el ID del Paciente del que desea asignar la cita"));
        int idMedicoCita = Integer.parseInt(JOptionPane.showInputDialog(listMedicos + "\nIngresa el ID del Medico del que desea asignar la cita"));
        String fechaCita = JOptionPane.showInputDialog("Ingresa la fecha a programar la cita \n En este formato YYYY-MM-DD");
        String horaCita = JOptionPane.showInputDialog("Ingresa la hora de la cita \n En este formato HH:MM:SS");
        String motivoCita = JOptionPane.showInputDialog("Escriba el motivo de la cita");

        Cita objCita = new Cita();
        objCita.setIdPaciente(idPacienteCita);
        objCita.setIdMedico(idMedicoCita);
        objCita.setFecha_cita(fechaCita);
        objCita.setHora_cita(horaCita);
        objCita.setMotivo(motivoCita);

        objCita = (Cita) objCitaModel.insert(objCita);

        JOptionPane.showMessageDialog(null, objCita.toString());

    }

    public static void getAll() {

        CitaModel objModel = new CitaModel();

        String listCitas = "LISTA DE CITAS \n";

        for (Object cita : objModel.findAll()) {
            Cita objCita = (Cita) cita;
            listCitas += objCita.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listCitas);
    }

    public static String getAllString() {

        CitaModel objModel = new CitaModel();

        String listCitas = "LISTA DE CITAS \n";

        for (Object cita : objModel.findAll()) {
            Cita objCita = (Cita) cita;
            listCitas += objCita.toString() + "\n";
        }

        return listCitas;
    }

    public static void update() {

        CitaModel objCitaModel = new CitaModel();

        String listCitas = getAllString();

        String listPacientes = PacienteController.getAllString();
        String listMedicos = MedicoController.getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCitas + "\nIngresa el ID de la cita que desea editar"));

        Cita objCita = objCitaModel.findById(idUpdate);

        if (objCita == null) {
            JOptionPane.showMessageDialog(null, "No se encontro la cita");
        } else {

            int idPaciente = Integer.parseInt(JOptionPane.showInputDialog(null, listPacientes + "\n Modifica el ID del paciente", objCita.getIdPaciente()));
            int idMedico = Integer.parseInt(JOptionPane.showInputDialog(null,listMedicos + "\n Modifica el ID del Medico", objCita.getIdMedico()));
            String fechaCita = JOptionPane.showInputDialog(null, "Modifica la fecha de la cita", objCita.getFecha_cita());
            String horaCita = JOptionPane.showInputDialog(null, "Modifica la hora de la cita", objCita.getHora_cita());
            String motivo = JOptionPane.showInputDialog(null, "Modifica el motivo de la cita", objCita.getMotivo());

            objCita.setIdPaciente(idPaciente);
            objCita.setIdMedico(idMedico);
            objCita.setFecha_cita(fechaCita);
            objCita.setHora_cita(horaCita);
            objCita.setMotivo(motivo);

            objCitaModel.update(objCita);
        }
    }

    public static void delete() {

        CitaModel objModel = new CitaModel();

        String listCitas = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listCitas + "\n Ingresa el ID de la cita que desea eliminar"));

        Cita objCita = objModel.findById(idDelete);

        int confirm = 1;

        if (objCita == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el id de la cita");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro que desea eliminar esta Cita?\nEsta opcion no se puede deshacer");
            if (confirm == 0) objModel.delete(objCita);
        }

    }

    public static void buscarPorFecha(){

        CitaModel objModel = new CitaModel();

        String fechaBuscar = JOptionPane.showInputDialog("Escriba la fecha en la que desea buscar citas \n En este Formato YYYY-MM-DD");

        if (objModel.buscarCitaPorFecha(fechaBuscar).isEmpty()){
            JOptionPane.showMessageDialog(null,"No hay ninguna cita en esta fecha");
        } else {
            JOptionPane.showMessageDialog(null,objModel.buscarCitaPorFecha(fechaBuscar).toString());
        }

    }


}
