package controller;

import entity.Avion;
import model.AvionModel;

import javax.swing.*;

public class AvionController {

    public static void create() {

        AvionModel objAvionModel = new AvionModel();

        String modeloAvion = JOptionPane.showInputDialog("Ingresa el modelo del avion");
        int capacidadAvion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad del avion"));

        Avion objAvion = new Avion();
        objAvion.setModelo(modeloAvion);
        objAvion.setCapacidad(capacidadAvion);

        objAvion = (Avion) objAvionModel.insert(objAvion);

        JOptionPane.showMessageDialog(null, objAvion.toString());

    }


    public static void getAll() {

        AvionModel objModel = new AvionModel();

        String listAviones = "LISTA DE AVIONES \n";

        for (Object avion : objModel.findAll()) {
            Avion objAvion = (Avion) avion;
            listAviones += objAvion.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listAviones);
    }

    public static String getAllString() {

        AvionModel objModel = new AvionModel();

        String listAviones = "LISTA DE AVIONES \n";

        for (Object avion : objModel.findAll()) {
            Avion objAvion = (Avion) avion;
            listAviones += objAvion.toString() + "\n";
        }

        return listAviones;
    }

    public static void update() {

        AvionModel objAvionModel = new AvionModel();

        String listAviones = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAviones + "\nIngresa el ID del Avion que desea editar"));

        Avion objAvion = objAvionModel.findById(idUpdate);

        if (objAvion == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el avion");
        } else {

            String modelo = JOptionPane.showInputDialog(null, "Modifica el modelo del avion", objAvion.getModelo());
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog(null, "\n Ingresa la nueva capacidad del avion", objAvion.getCapacidad()));

            objAvion.setModelo(modelo);
            objAvion.setCapacidad(capacidad);

            objAvionModel.update(objAvion);
        }
    }


    public static void delete() {

        AvionModel objModel = new AvionModel();

        String listAviones = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAviones + "\n Ingresa el id del avion que desea eliminar"));

        Avion objAvion = objModel.findById(idDelete);

        int confirm = 1;

        if (objAvion == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el id del Avion");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro que desea eliminar este Avion?\nEsta opcion no se puede deshacer");
            if (confirm == 0) objModel.delete(objAvion);
        }

    }

}
