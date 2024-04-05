package entity;

public class Vuelo {

    private int id_vuelo;
    private String destino;
    private String fecha_salida;
    private String hora_salida;
    private int idAvion;

    public Vuelo(){

    }

    public Vuelo(int id_vuelo, String destino, String fecha_salida, String hora_salida, int idAvion) {
        this.id_vuelo = id_vuelo;
        this.destino = destino;
        this.fecha_salida = fecha_salida;
        this.hora_salida = hora_salida;
        this.idAvion = idAvion;
    }

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    @Override
    public String toString() {
        return "Vuelo" + id_vuelo + "{" + "\n" +
                " - idVuelo = " + id_vuelo + "\n" +
                " - Destino = " + destino + "\n" +
                " - Fecha Salida = " + fecha_salida + "\n" +
                " - Hora Salida = " + hora_salida + "\n" +
                " - idAvion = " + idAvion + '}' + "\n";
    }
}
