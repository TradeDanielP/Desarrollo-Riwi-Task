public class Estudiante {
    private int id;
    private String nombre;
    private String email;


    public Estudiante(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;


    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Estudiante{ \n" +
                "id = " + id + "\n" +
                "nombre = " + nombre + '\n' +
                "email = " + email + '\n' +
                '}';
    }
}
