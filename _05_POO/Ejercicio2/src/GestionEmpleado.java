import java.util.ArrayList;
import java.util.Scanner;

public class GestionEmpleado {
    private ArrayList<Empleado> gestionEmpleado;

    public GestionEmpleado() {
        this.gestionEmpleado = new ArrayList<>();
    }

    public void agregarEmpleado(Scanner objscan) {

        System.out.println("""
                Seleccione el tipo de empleado que desea agregar
                1. Temporal
                2. Permanente
                """);
        int codigo = objscan.nextInt();

        if (codigo == 1) {
            objscan.nextLine();
            System.out.println("ingrese el nombre del empleado: ");
            String nombre = objscan.nextLine();

            System.out.println("ingrese la cedula del empleado: ");
            int id = objscan.nextInt();

            System.out.println("ingrese la edad: ");
            int edad = objscan.nextInt();

            System.out.println("Ingrese el salario");
            Double salario = objscan.nextDouble();

            EmpleadoTemporal empleadoTemp = new EmpleadoTemporal(id, salario, "Temporal", nombre, edad);

            if (this.gestionEmpleado.add(empleadoTemp)) {
                System.out.println("Empleado agregado correctamente");
            } else {
                System.out.println("El empleado no se puedo agregar");
            }

        } else {
            objscan.nextLine();

            System.out.println("ingrese el nombre del empleado: ");
            String nombre = objscan.nextLine();

            System.out.println("ingrese la cedula del empleado: ");
            int id = objscan.nextInt();

            System.out.println("ingrese la edad: ");
            int edad = objscan.nextInt();

            System.out.println("Ingrese el salario: ");
            Double salario = objscan.nextDouble();

            EmpleadoPermanente empleadoPer = new EmpleadoPermanente(id, salario, "Permanente", nombre, edad);

            if (gestionEmpleado.add(empleadoPer)) {
                System.out.println("Empleado agregado correctamente");
            } else {
                System.out.println("El empleado no se puedo agregar");
            }
        }

    }

    public void listarEmpleado() {

        for (Empleado empleado : this.gestionEmpleado) {
            System.out.println(empleado.toString());
        }
    }

    public void eliminarEmpleado(Scanner objscan){

        for (Empleado empleado : this.gestionEmpleado) {
            System.out.println(empleado.toString());
        }

        int empleadoEliminar = objscan.nextInt();

        boolean eliminado = this.gestionEmpleado.removeIf(empleado -> empleado.getIdEmpleado() == empleadoEliminar);

        System.out.println(!eliminado ? "No se pudo eliminar al Empleado" : "Empleado eliminado correctamente");

    }


}
