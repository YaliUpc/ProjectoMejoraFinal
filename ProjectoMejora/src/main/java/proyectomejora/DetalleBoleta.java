package main.java.proyectomejora;

public class DetalleBoleta {
    private int cant;
    private String nombre;
    private double precio;

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public DetalleBoleta(int cant, String nombre, double precio) {
        this.cant = cant;
        this.nombre = nombre;
        this.precio = precio;
    }
}
