package controller;

import entity.Avion;
import entity.Reservacion;
import entity.Vuelo;
import model.AvionModel;
import model.ReservacionModel;
import model.VueloModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class   ReservacionController {

    public static void create() {

        ReservacionModel objReservacionModel = new ReservacionModel();

        String listPasajeros = PasajeroController.getAllString();
        String listVuelos = VueloController.getAllString();

        int idPasajero = Integer.parseInt(JOptionPane.showInputDialog(listPasajeros + "\n Ingresa el ID del Pasajero que hizo esta reservacion"));
        int idVuelo = Integer.parseInt(JOptionPane.showInputDialog(listVuelos + "\n Ingresa el ID del Vuelo al que deseas asignar esta reservacion"));
        if(verifyCapacity(idVuelo)){
            String fechaReservacion = JOptionPane.showInputDialog("Ingresa la fecha en que se esta haciendo la reservacion \n en este formato: YYYY-MM-DD");
            String asiento = JOptionPane.showInputDialog("Ingrese el Asiento");
            if (verifyAsiento(idVuelo,asiento)){

                Reservacion objReservacion = new Reservacion();
                objReservacion.setIdPasajero(idPasajero);
                objReservacion.setIdVuelo(idVuelo);
                objReservacion.setFecha_reservacion(fechaReservacion);
                objReservacion.setAsiento(asiento);

                objReservacion = (Reservacion) objReservacionModel.insert(objReservacion);


                JOptionPane.showMessageDialog(null, objReservacion.toString());
            }else {
                JOptionPane.showMessageDialog(null,"Este asiento ya se encuentra reservado");
            }


        } else {
            JOptionPane.showMessageDialog(null,"Este Vuelo, ya no tiene mas capacidad");
        }

    }

    public static boolean verifyCapacity(int idVuelo){

        boolean result = true;

        ReservacionModel objReservaModel = new ReservacionModel();

        Reservacion objReservacion = new Reservacion();
        objReservacion.setIdVuelo(idVuelo);

        Vuelo avion = new VueloModel().findById(idVuelo);

        int count = objReservaModel.count(idVuelo);
        int capacidad = new AvionModel().findById(avion.getIdAvion()).getCapacidad();

        if(count >= capacidad){
            result = false;
        }

        return result;
    }

    public static boolean verifyAsiento(int idVuelo, String asiento){

        boolean result = true;

        ReservacionModel objReservaModel = new ReservacionModel();
        Reservacion objReservacion = new Reservacion();
        objReservacion.setIdVuelo(idVuelo);

        ArrayList<Object> asientos = objReservaModel.buscarAsientosPorReserva(idVuelo);

        if (asientos.contains(asiento)) result = false;

        return result;
    }

    public static void getAll() {

        ReservacionModel objModel = new ReservacionModel();

        String listReservaciones = "LISTA DE RESERVACIONES \n";

        for (Object reservacion : objModel.findAll()) {
            Reservacion objReservacion = (Reservacion) reservacion;
            listReservaciones += objReservacion.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listReservaciones);
    }

    public static String getAllString() {

        ReservacionModel objModel = new ReservacionModel();

        String listReservaciones = "LISTA DE RESERVACIONES \n";

        for (Object reservacion : objModel.findAll()) {
            Reservacion objReservacion = (Reservacion) reservacion;
            listReservaciones += objReservacion.toString() + "\n";
        }

        return listReservaciones;
    }

    public static void update() {

        ReservacionModel objReservacionModel = new ReservacionModel();

        String listReservaciones = getAllString();
        String listPasajeros = PasajeroController.getAllString();
        String listVuelos = VueloController.getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listReservaciones + "\nIngresa el ID de la Reservacion que desea editar"));

        Reservacion objReservacion = objReservacionModel.findById(idUpdate);

        if (objReservacion == null) {
            JOptionPane.showMessageDialog(null, "No se encontro la reserva");
        } else {
            int idPasajero = Integer.parseInt(JOptionPane.showInputDialog(null,listPasajeros + "\n Ingresa el ID del nuevo Pasajero si desea cambiarlo \n de lo contrario deje el ID que ya esta",objReservacion.getIdPasajero()));
            int idVuelo = Integer.parseInt(JOptionPane.showInputDialog(null,listVuelos + "\n Ingresa el ID del nuevo Vuelo si desea cambiarlo \n de lo contrario deje el ID que ya esta",objReservacion.getIdVuelo()));
            String fechaReserva = JOptionPane.showInputDialog(null, "Modifica la fecha de la Reserva", objReservacion.getFecha_reservacion());
            String asiento = JOptionPane.showInputDialog(null, "Modifica el asiento del Vuelo", objReservacion.getAsiento());

            objReservacion.setIdPasajero(idPasajero);
            objReservacion.setIdVuelo(idVuelo);
            objReservacion.setFecha_reservacion(fechaReserva);
            objReservacion.setAsiento(asiento);

            objReservacionModel.update(objReservacion);
        }
    }

    public static void delete() {

        ReservacionModel objModel = new ReservacionModel();

        String listReservaciones = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listReservaciones + "\n Ingresa el id de la Reserva que desea eliminar"));

        Reservacion objReservacion = objModel.findById(idDelete);

        int confirm = 1;

        if (objReservacion == null) {
            JOptionPane.showMessageDialog(null, "No se encontro el id de la Reserva");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro que desea eliminar esta Reserva?\nEsta opcion no se puede deshacer");
            if (confirm == 0) objModel.delete(objReservacion);
        }
    }


    public static void buscarReservasPorVuelo(){

        ReservacionModel objModel = new ReservacionModel();

        String listVuelos = VueloController.getAllString();

        int idVuelo = Integer.parseInt(JOptionPane.showInputDialog(listVuelos + "\n Digite el ID del Vuelo en la que desea mostrar las reservas"));

        if (objModel.buscarReservacionesPorVuelo(idVuelo).isEmpty()){
            JOptionPane.showMessageDialog(null,"No hay ninguna Reserva con en este Vuelo");
        } else {
            JOptionPane.showMessageDialog(null,objModel.buscarReservacionesPorVuelo(idVuelo).toString());
        }

    }


}
