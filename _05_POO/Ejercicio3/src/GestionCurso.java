import java.util.ArrayList;
import java.util.Scanner;

public class GestionCurso {
    private ArrayList<Curso> listaCursos;

    public GestionCurso() {
        this.listaCursos = new ArrayList<>();
    }

    public void agregarCurso(Scanner objscan) {

        System.out.println("Ingrese el codigo del curso");
        String codigo = objscan.next();
        System.out.println("Ingrese el nombre del curso");
        String nombre = objscan.next();

        //Validamos el codigo del curso para que el codigo sea unico
        if (this.buscarCurso(codigo) != null) {

            System.out.println("Ya existe un curso con este codigo");

        } else {

            Curso curso = new Curso(codigo, nombre);

            if (this.listaCursos.add(curso)) {

                System.out.println("Curso agregar correctamente");

            } else {

                System.out.println("No se pudo agregar el curso");
            }
        }


    }

    public Curso buscarCurso(String codigoBuscar) {
        for (Curso curso : this.listaCursos) {
            if (curso.getCodigo().equalsIgnoreCase(codigoBuscar)) {
                return curso;
            }
        }
        return null;
    }

    public void listarCursos() {

        if (this.listaCursos.isEmpty()) {
            System.out.println("No se puede mostrar lista vacia");

        } else {
            System.out.println("lista de cursos");
            for (Curso curso : this.listaCursos) {
                System.out.println(curso.toString());
            }
        }
    }
}