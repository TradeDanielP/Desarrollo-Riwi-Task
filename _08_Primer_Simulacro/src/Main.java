import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;

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
                    1. Gestionar Especialidades
                    2. Gestionar Medicos
                    3. Gestionar Pacientes
                    4. Gestionar Citas
                    5. Salir
                    """);

            switch (option) {

                case "1":
                    do {

                        option1 = JOptionPane.showInputDialog("""
                                1. Crear Especialidad
                                2. Mostrar Especialidades
                                3. Editar Especialidad
                                4. Eliminar Especialidad
                                5. Buscar Medicos Por Especialidad
                                6. Salir
                                """);

                        switch (option1) {

                            case "1":
                                EspecialidadController.create();
                                break;
                            case "2":
                                EspecialidadController.getAll();
                                break;
                            case "3":
                                EspecialidadController.update();
                                break;
                            case "4":
                                EspecialidadController.delete();
                                break;
                            case "5":
                                EspecialidadController.buscarMedicoPorEspecialidad();
                                break;

                        }

                    } while (!option1.equals("6"));
                    break;
                case "2":
                    do {

                        option2 = JOptionPane.showInputDialog("""
                                1. Crear Medico
                                2. Mostrar Medicos
                                3. Editar Medico
                                4. Eliminar Medico
                                5. Salir
                                """);

                        switch (option2) {

                            case "1":
                                MedicoController.create();
                                break;
                            case "2":
                                MedicoController.getAll();
                                break;
                            case "3":
                                MedicoController.update();
                                break;
                            case "4":
                                MedicoController.delete();
                                break;

                        }
                    } while (!option2.equals("5"));
                    break;
                case "3":
                    do {
                        option3 = JOptionPane.showInputDialog("""
                                1. Crear Paciente
                                2. Mostrar Pacientes
                                3. Editar Paciente
                                4. Eliminar Paciente
                                5. Buscar Paciente por Documento
                                6. Salir
                                """);

                        switch (option3) {

                            case "1":
                                PacienteController.create();
                                break;
                            case "2":
                                PacienteController.getAll();
                                break;
                            case "3":
                                PacienteController.update();
                                break;
                            case "4":
                                PacienteController.delete();
                                break;
                            case "5":
                                PacienteController.buscarPorDocumento();
                                break;
                        }
                    } while (!option3.equals("6"));
                    break;
                case "4":
                    do {
                        option4 = JOptionPane.showInputDialog("""
                                1. Asignar Cita
                                2. Mostrar Citas
                                3. Editar Cita
                                4. Eliminar Cita
                                5. Buscar Citas
                                6. Salir
                                """);

                        switch (option4) {
                            case "1":
                                CitaController.assign();
                                break;
                            case "2":
                                CitaController.getAll();
                                break;
                            case "3":
                                CitaController.update();
                                break;
                            case "4":
                                CitaController.delete();
                                break;
                            case "5":
                                CitaController.buscarPorFecha();
                                break;

                        }
                    } while (!option4.equals("6"));
                    break;
            }

        } while (!option.equals("5"));

    }
}