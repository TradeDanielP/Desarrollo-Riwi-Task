public class Empleado extends Persona {
    private int idEmpleado;
    private Double salario;
    private String tipoEmpleado;

    public Empleado(int idEmpleado, Double salario, String tipoEmpleado, String nombre, int edad) {
        super(nombre, edad);
        this.idEmpleado = idEmpleado;
        this.salario = salario;
        this.tipoEmpleado = tipoEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;


    }


    public Double getSalario() {
        return salario;

    }

    public void setSalario() {
        this.salario = salario;

    }

    public void setIdEmpleado() {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "id Empleado = " + idEmpleado + "\n" +
                "Salario = " + salario + "\n" +
                "tipo Empleado = " + tipoEmpleado + " }\n";
    }
}
