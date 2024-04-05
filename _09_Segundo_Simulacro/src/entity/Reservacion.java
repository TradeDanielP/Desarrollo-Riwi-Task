package entity;

public class Reservacion {

    private int id_reservacion;
    private int idPasajero;
    private int idVuelo;
    private String fecha_reservacion;
    private String asiento;

    public Reservacion(){

    }

    public Reservacion(int id_reservacion, int idPasajero, int idVuelo, String fecha_reservacion, String asiento) {
        this.id_reservacion = id_reservacion;
        this.idPasajero = idPasajero;
        this.idVuelo = idVuelo;
        this.fecha_reservacion = fecha_reservacion;
        this.asiento = asiento;
    }

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getFecha_reservacion() {
        return fecha_reservacion;
    }

    public void setFecha_reservacion(String fecha_reservacion) {
        this.fecha_reservacion = fecha_reservacion;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    @Override
    public String toString() {
        return "Reservacion" + id_reservacion + "{" + "\n" +
                " - idReservacion = " + id_reservacion + "\n" +
                " - idPasajero = " + idPasajero + "\n" +
                " - idVuelo = " + idVuelo + "\n" +
                " - Fecha Reservacion = " + fecha_reservacion + "\n" +
                " - Asiento = " + asiento + "}" + "\n";
    }
}
