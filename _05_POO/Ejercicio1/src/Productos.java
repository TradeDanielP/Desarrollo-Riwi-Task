public class Productos {

    //Atributos

    private int id;
    private String nombre;
    private double precio;

    //Constructor

    public Productos(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Setter and Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double isPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    //Metodos

    @Override
    public String toString() {
        return "Productos{" + "\n" +
                "id = " + id + "\n" +
                "nombre = " + nombre + '\n' +
                "precio = " + precio + "\n";
    }
}
