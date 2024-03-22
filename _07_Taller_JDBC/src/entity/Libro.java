package entity;

public class Libro {

    private int id;
    private String title;
    private String publication_date;
    private double price;
    private int autorId;

    public Libro(){
    }

    public Libro(int id, String title, String publication_date, double price, int autorId) {
        this.id = id;
        this.title = title;
        this.publication_date = publication_date;
        this.price = price;
        this.autorId = autorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    @Override
    public String toString() {
        return "Libro{" + "\n" +
                "id = " + id + "\n" +
                "title = " + title + '\n' +
                "publication_date = " + publication_date + '\n' +
                "price = " + price + "\n" +
                "autorId = " + autorId + '}' + "\n";
    }
}
