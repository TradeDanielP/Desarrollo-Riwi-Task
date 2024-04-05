package controller;

import entity.Vuelo;
import model.VueloModel;

import javax.swing.*;
import java.util.List;

public class VueloController {

    public static void create() {

        VueloModel objVueloModel = new VueloModel();

        String listAviones = AvionController.getAllString();

        String destinoVuelo = JOptionPane.showInputDialog("Ingresa el destino de este vuelo");
        String fechaSalidaVuelo = JOptionPane.showInputDialog("Ingresa la fecha de salida del vuelo \n en este formato: YYYY-MM-DD");
        String horaSalidaVuelo = JOptionPane.showInputDialog("Ingresa la hora de salida del vuelo \n en este formato: HH:MM:SS");
        int idAvionVuelo = Integer.parseInt(JOptionPane.showInputDialog(listAviones + "\n Ingresa el ID del Avion al que esta asignado este vuelo"));

        Vuelo objVuelo = new Vuelo();
        objVuelo.setDestino(destinoVuelo);
        objVuelo.setFecha_salida(fechaSalidaVuelo);
        objVuelo.setHora_salida(horaSalidaVuelo);
        objVuelo.setIdAvion(idAvionVuelo);

        objVuelo = (Vuelo) objVueloModel.insert(objVuelo);

        JOptionPane.showMessageDialog(null, objVuelo.toString());
    }


    public static void getAll() {

        VueloModel objModel = new VueloModel();

        String listVuelos = "LISTA DE VUELOS \n";

        for (Object vuelo : objModel.findAll()) {
            Vuelo objVuelo = (Vuelo) vuelo;
            listVuelos += objVuelo.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listVuelos);
    }

    public static String getAllString() {

        VueloModel objModel = new VueloModel();

        String listVuelos = "LISTA DE VUELOS \n";

        for (Object vuelo : objModel.findAll()) {
            Vuelo objVuelo = (Vuelo) vuelo;
            listVuelos += objVuelo.toString() + "\n";
        }

        return listVuelos;
    }

    public static void update() {

        VueloModel objVueloModel = new VueloModel();

        String listVuelos = getAllString();
        String listAviones = AvionController.getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listVuelos + "\nIngresa el ID del Vuelo que desea editar"));

        Vuelo objVuelo = objVueloModel.findById(idUpdate);

        if (objVuelo == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el Vuelo");
        } else {

            String destino = JOptionPane.showInputDialog(null, "Modifica el destino del vuelo", objVuelo.getDestino());
            String fechaSalida = JOptionPane.showInputDialog(null, "Modifica la fecha de salida del vuelo en el mismo formato", objVuelo.getFecha_salida());
            String horaSalida = JOptionPane.showInputDialog(null, "Modifica la hora de salida del vuelo en el mismo formato", objVuelo.getHora_salida());
            int idAvion = Integer.parseInt(JOptionPane.showInputDialog(null,listAviones + "\n Ingresa el ID del nuevo Vuelo si desea cambiarlo \n de lo contrario deje el ID que ya esta",objVuelo.getIdAvion()));

            objVuelo.setDestino(destino);
            objVuelo.setFecha_salida(fechaSalida);
            objVuelo.setHora_salida(horaSalida);
            objVuelo.setIdAvion(idAvion);

            objVueloModel.update(objVuelo);
        }
    }


    public static void delete() {

        VueloModel objModel = new VueloModel();

        String listVuelos = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listVuelos + "\n Ingresa el id del Vuelo que desea eliminar"));

        Vuelo objVuelo = objModel.findById(idDelete);

        int confirm = 1;

        if (objVuelo == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el id del Vuelo");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro que desea eliminar este Vuelo?\nEsta opcion no se puede deshacer");
            if (confirm == 0) objModel.delete(objVuelo);
        }

    }

    public static void buscarPorDestino(){

        VueloModel objModel = new VueloModel();

        String destinoBuscar = JOptionPane.showInputDialog("Escriba el Destino al que desea Viajar para mostrar los vuelos disponibles");

        if (objModel.buscarVuelosPorDestino(destinoBuscar).isEmpty()){
            JOptionPane.showMessageDialog(null,"No Hay ningun Vuelo para este Destino");
        } else {
            JOptionPane.showMessageDialog(null,objModel.buscarVuelosPorDestino(destinoBuscar).toString());
        }
    }

}
