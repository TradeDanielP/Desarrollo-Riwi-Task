package controller;

import entity.Pasajero;
import model.PasajeroModel;

import javax.swing.*;

public class PasajeroController {

    public static void create() {

        PasajeroModel objPasajeroModel = new PasajeroModel();

        String nombrePasajero = JOptionPane.showInputDialog("Ingresa los nombres del Pasajero");
        String apellidoPasajero = JOptionPane.showInputDialog("Ingresa los apellidos del Pasajero");
        String docuemntoPasajero = JOptionPane.showInputDialog("Ingresa el numero de documento del Pasajero");

        Pasajero objPasajero = new Pasajero();
        objPasajero.setNombre(nombrePasajero);
        objPasajero.setApellido(apellidoPasajero);
        objPasajero.setDocumento_identidad(docuemntoPasajero);

        objPasajero = (Pasajero) objPasajeroModel.insert(objPasajero);

        JOptionPane.showMessageDialog(null, objPasajero.toString());

    }


    public static void getAll() {

        PasajeroModel objModel = new PasajeroModel();

        String listPasajeros = "LISTA DE PASAJEROS \n";

        for (Object pasajero : objModel.findAll()) {
            Pasajero objPasajero = (Pasajero) pasajero;
            listPasajeros += objPasajero.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listPasajeros);
    }

    public static String getAllString() {

        PasajeroModel objModel = new PasajeroModel();

        String listPasajeros = "LISTA DE PASAJEROS \n";

        for (Object pasajero : objModel.findAll()) {
            Pasajero objPasajero = (Pasajero) pasajero;
            listPasajeros += objPasajero.toString() + "\n";
        }

        return listPasajeros;
    }

    public static void update() {

        PasajeroModel objPasajeroModel = new PasajeroModel();

        String listPasajeros = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listPasajeros + "\nIngresa el ID del Pasajero que desea editar"));

        Pasajero objPasajero = objPasajeroModel.findById(idUpdate);

        if (objPasajero == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el Pasajero");
        } else {

            String nombre = JOptionPane.showInputDialog(null, "Modifica el nombre del Pasajero", objPasajero.getNombre());
            String apellido = JOptionPane.showInputDialog(null, "Modifica el apellido del Pasajero", objPasajero.getApellido());
            String documento = JOptionPane.showInputDialog(null, "Modifica el documento del pasajero", objPasajero.getDocumento_identidad());

            objPasajero.setNombre(nombre);
            objPasajero.setApellido(apellido);
            objPasajero.setDocumento_identidad(documento);

            objPasajeroModel.update(objPasajero);
        }
    }


    public static void delete() {

        PasajeroModel objModel = new PasajeroModel();

        String listPasajeros = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPasajeros + "\n Ingresa el id del Pasajero que desea eliminar"));

        Pasajero objPasajero = objModel.findById(idDelete);

        int confirm = 1;

        if (objPasajero == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el id del Pasajero");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro que desea eliminar este Pasajero?\nEsta opcion no se puede deshacer");
            if (confirm == 0) objModel.delete(objPasajero);
        }
    }

    public static void buscarPorNombre(){
        PasajeroModel objModel = new PasajeroModel();

        String nombreBuscar = JOptionPane.showInputDialog("Escriba el nombre del Pasajero que desea Buscar");

        if (objModel.buscarPasajeroPorNombre(nombreBuscar).isEmpty()){
            JOptionPane.showMessageDialog(null,"No se encontro ningun Pasajero registrado con este nombre");
        }else {
            JOptionPane.showMessageDialog(null,objModel.buscarPasajeroPorNombre(nombreBuscar).toString());
        }
    }

}
