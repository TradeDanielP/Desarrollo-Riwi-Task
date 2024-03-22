import controller.AutorController;
import controller.LibroController;
import database.ConfigDB;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String option = "";
        String option2 = "";
        String option3 = "";


        do {

            option = JOptionPane.showInputDialog("""
                    1. Autores
                    2. Libros
                    3. Salir
                                  
                    Elige una opción:
                    """);

            switch (option) {

                case "1":

                    do {
                        option2 = JOptionPane.showInputDialog("""
                                1. Listar Autores
                                2. Insertar Autor
                                3. Actualizar Autor
                                4. Borrar Autor
                                5. Exit
                                                    
                                Choose an option:
                                """);

                        switch (option2) {

                            case "1":
                                AutorController.getAll();
                                break;
                            case "2":
                                AutorController.create();
                                break;
                            case "3":
                                AutorController.update();
                                break;
                            case "4":
                                AutorController.delete();
                                break;

                        }

                    } while (!option2.equals("5"));

                    break;
                case "2":

                    do {
                        option2 = JOptionPane.showInputDialog("""
                                1. Listar Libros
                                2. Insertar Libro
                                3. Actualizar Libro
                                4. Borrar Libro
                                5. Buscar Libro
                                6. Salir
                                                    
                                Elige una opción:
                                """);

                        switch (option2) {

                            case "1":
                                LibroController.getAll();
                                break;
                            case "2":
                                LibroController.create();
                                break;
                            case "3":
                                LibroController.update();
                                break;
                            case "4":
                                LibroController.delete();
                                break;
                            case "5":
                                do {
                                    option3 = JOptionPane.showInputDialog("""
                                            1. Buscar libros por nombre
                                            2. Buscar libros por Autor
                                            3. Salir
                                                                
                                            Elige una opción:
                                            """);

                                    switch (option3) {

                                        case "1":
                                            JOptionPane.showMessageDialog(null,"Pendiente");
                                            break;
                                        case "2":
                                            JOptionPane.showMessageDialog(null,"Pendiente");
                                            break;
                                    }

                                } while (!option3.equals("3"));

                                break;
                        }
                    } while (!option2.equals("6"));


            }
        }while (!option.equals("3"));
    }
}
