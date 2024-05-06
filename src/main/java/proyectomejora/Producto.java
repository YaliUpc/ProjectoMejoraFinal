package main.java.proyectomejora;

public class Almacen {
    private String nombre;
    private String descripcion;
    private String talla;
    private double precio;
    private int cantidad;

    public Almacen(String nombre, String descripcion, String talla, double precio, int cantidad) {
        this.nombre = nombre.toUpperCase();
        this.descripcion = descripcion.toUpperCase();
        this.talla = talla.toUpperCase();
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
