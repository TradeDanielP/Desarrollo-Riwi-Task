import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int opcion = 0;
        int opcion2 = 0;
        Scanner objScan = new Scanner(System.in);
        GestionCurso gestionCurso = new GestionCurso();
        do {
            System.out.println("MENU DE CURSOS \n" +
                    "1.Administrar Estudiantes \n" +
                    "2.Administrar Cursos \n" +
                    "3.Salir \n" +

                    "\n ingrese una opcion: ");
            opcion = objScan.nextInt();
            switch (opcion) {
                case 1:

                    int option3 = 0;
                    do {
                        System.out.println("""
                                MENU DE ESTUDIANTES
                                1. Agregar estudiante a un curso
                                2. Listar todos los estudiantes de un curso
                                3. Eliminar un estudiante de un curso
                                4. Salir
                                
                                Ingresa una opcion: 
                                """);

                        option3 = objScan.nextInt();

                        switch (option3){
                            case 1:
                                gestionCurso.listarCursos();
                                System.out.println("Ingrese el codigo del curso donde" +
                                        "Ingresaras el nuevo estudiante");
                                String codigo = objScan.next();
                                Curso objCurso = gestionCurso.buscarCurso(codigo);

                                if(objCurso == null){
                                    System.out.println("El codigo ingresado no es valido");
                                }else {
                                    objCurso.agregarEstudiante(objScan);
                                }
                                break;
                            case 2:

                                gestionCurso.listarCursos();

                                break;
                            case 3:
                                //1. Listar Cursos
                                gestionCurso.listarCursos();
                                // 2. Preguntar el codigo
                                System.out.println("Ingrese el codigo del curso");
                                codigo = objScan.next();
                                // 3. Buscar el curso que tenga este codigo
                                objCurso = gestionCurso.buscarCurso(codigo);
                                if(objCurso == null){
                                    System.out.println("El codigo ingresado no es valido");
                                }else { // 4. Eliminar el estudiante de ese curso
                                    objCurso.eliminarEstudiante(objScan);
                                    break;
                                }
                        }

                    } while (option3 != 4);

                    break;
                case 2:
                    do {
                        System.out.println("Menu de cursos \n" +
                                "1.agregar cursos \n" +
                                "2.listar cursos \n" +
                                "3.buscar cursos \n" +
                                "4.salir \n" +

                                "\nIngrese una opcion: ");
                        opcion2 = objScan.nextInt();
                        switch (opcion2) {
                            case 1:
                                gestionCurso.agregarCurso(objScan);


                                break;
                            case 2:
                                gestionCurso.listarCursos();


                                break;
                            case 3:

                                System.out.println("ingresar codigo a buscar");
                                String tipo = objScan.next();
                                Curso objCurso = gestionCurso.buscarCurso(tipo);
                                if (objCurso == null) {

                                    System.out.println("no existe");
                                } else {

                                    System.out.println(objCurso.toString());
                                }


                                break;

                        }


                    }
                    while (opcion2 != 4);


                    break;

            }
        }
        while (opcion != 3);


        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

    }
}