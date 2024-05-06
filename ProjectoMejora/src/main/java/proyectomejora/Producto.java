package main.java.proyectomejora;

public class Producto {
    private int codigo;
    private String nombre;
    private String modelo;
    private String talla;
    private double precio;
    private int cantidad;

public Producto(){
}

    public Producto(String nombre, String modelo, String talla, double precio, int cantidad) {
        this.nombre = nombre.toUpperCase();
        this.modelo = modelo.toUpperCase();
        this.talla = talla.toUpperCase();
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Producto(int codigo, String nombre, String modelo, String talla, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.modelo = modelo;
        this.talla = talla;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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
