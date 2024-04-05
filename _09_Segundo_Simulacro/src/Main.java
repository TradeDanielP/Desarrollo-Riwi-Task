import controller.AvionController;
import controller.PasajeroController;
import controller.ReservacionController;
import controller.VueloController;
import model.ReservacionModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        String option = "";
        String option1 = "";
        String option2 = "";
        String option3 = "";
        String option4 = "";

        do {

            option = JOptionPane.showInputDialog("""
                    1. Gestionar Aviones
                    2. Gestionar Pasajeros
                    3. Gestionar Reservaciones
                    4. Gestionar Vuelos
                    5. Salir
                    """);

            switch (option) {

                case "1":
                    do {

                        option1 = JOptionPane.showInputDialog("""
                                1. Crear Avion
                                2. Mostrar Aviones
                                3. Editar Avion
                                4. Eliminar Avion
                                5. Salir
                                """);

                        switch (option1) {

                           case "1":
                                AvionController.create();
                                break;
                            case "2":
                                AvionController.getAll();
                                break;
                            case "3":
                                AvionController.update();
                                break;
                            case "4":
                                AvionController.delete();
                                break;
                        }

                    } while (!option1.equals("5"));
                    break;
                case "2":
                    do {

                        option2 = JOptionPane.showInputDialog("""
                                1. Crear Pasajero
                                2. Mostrar Pasajeros
                                3. Editar Pasajero
                                4. Eliminar Pasajero
                                5. Buscar Pasajeros por Nombre
                                6. Salir
                                """);

                        switch (option2) {

                            case "1":
                                PasajeroController.create();
                                break;
                            case "2":
                                PasajeroController.getAll();
                                break;
                            case "3":
                                PasajeroController.update();
                                break;
                            case "4":
                                PasajeroController.delete();
                                break;
                            case "5":
                                PasajeroController.buscarPorNombre();
                                break;

                        }
                    } while (!option2.equals("6"));
                    break;
                case "3":
                    do {
                        option3 = JOptionPane.showInputDialog("""
                                1. Asignar Reservacion
                                2. Mostrar Reservaciones
                                3. Editar Reservacion
                                4. Eliminar Reservacion
                                5. Mostrar Las Reservaciones de un Vuelo Especifico
                                6. Salir
                                """);

                        switch (option3) {

                            case "1":
                                ReservacionController.create();
                                break;
                            case "2":
                                ReservacionController.getAll();
                                break;
                            case "3":
                                ReservacionController.update();
                                break;
                            case "4":
                                ReservacionController.delete();
                                break;
                            case "5":
                                ReservacionController.buscarReservasPorVuelo();
                                break;

                        }
                    } while (!option3.equals("6"));
                    break;
                case "4":
                    do {
                        option4 = JOptionPane.showInputDialog("""
                                1. Crear Vuelo
                                2. Mostrar Vuelos
                                3. Editar Vuelo
                                4. Eliminar Vuelo
                                5. Buscar Vuelos por Destino
                                6. Salir
                                """);

                        switch (option4) {
                            case "1":
                                VueloController.create();
                                break;
                            case "2":
                                VueloController.getAll();
                                break;
                            case "3":
                                VueloController.update();
                                break;
                            case "4":
                                VueloController.delete();
                                break;
                            case "5":
                                VueloController.buscarPorDestino();
                                break;

                        }
                    } while (!option4.equals("6"));
                    break;
            }

        } while (!option.equals("5"));

    }

}