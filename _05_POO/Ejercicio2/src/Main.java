import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner objscan = new Scanner(System.in);

        GestionEmpleado empleado = new GestionEmpleado();

        int opcion = 0;

        do {

            System.out.println("""
                MENU DE OPCIONES
                1. Agregar Empleado
                2. Listar Empleado
                3. Eliminar Empleado
                4. Salir
                
                Ingrese la opcion: 
                """);
            opcion = objscan.nextInt();

            switch (opcion){
                case 1:
                    empleado.agregarEmpleado(objscan);
                    break;
                case 2:
                    empleado.listarEmpleado();
                    break;
                case 3:
                    empleado.eliminarEmpleado(objscan);
                    break;
            }

        }while(opcion != 4);


        }
}