import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Scanner;

public class Curso {

    private String codigo;
    private String nombre;

    private ArrayList<Estudiante> listaEstudiantes;

    private static int index = 1;

    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.listaEstudiantes = new ArrayList<>();
    }

    public void agregarEstudiante(Scanner objscan){

        int id = 0;
        objscan.nextLine();
        System.out.println("Ingrese el nombre del estudiante");
        String nombre = objscan.nextLine();
        System.out.println("Ingrese el email del estudiante");
        String email = objscan.nextLine();

        Estudiante estudiante = new Estudiante(index,nombre,email);
        index++;

        if (this.listaEstudiantes.add(estudiante)) {
            System.out.println("Estudiante agregar correctamente");
        } else {
            System.out.println("No se pudo agregar al estudiante");
        }
    }

    public void listarEstudiante(){

        if(this.listaEstudiantes.isEmpty()){
            System.out.println("No hay estudiantes en este curso ".concat(this.nombre));
        } else {
            System.out.println("Lista de estudiantes del Curso ".concat(this.nombre));
            for (Estudiante estudiante : this.listaEstudiantes){
                System.out.println("- ".concat(estudiante.toString()));
            }
        }
    }

    public void eliminarEstudiante(Scanner objScan){
        this.listarEstudiante();
        System.out.println("Ingrese el id del estudiante a eliminar");
        int idEliminar = objScan.nextInt();

        boolean eliminado = this.listaEstudiantes.removeIf(estudiante -> estudiante.getId() == idEliminar);

        System.out.println(!eliminado ? "No se pudo eliminar el estudiante" : "Estudiante eliminado correctamente");
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    @Override
    public String toString() {
        return "Curso{ \n" +
                "codigo = " + codigo + '\n' +
                "nombre = " + nombre + '\n' +
                "listaEstudiantes = " + listaEstudiantes + "\n" +
                '}';
    }
}