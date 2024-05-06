package main.java.proyectomejora;

import java.util.Date;
import java.util.List;

public class Boleta {
    private String codigo;
    private Date fecha;
    private String dni;
    private String nombreCliente;
    private int cantidad;
    private String descripcion;
    private double precioUnitario;
    private double importeTotal;
    private List<DetalleBoleta> detalleBoletaList;

    public Boleta(String codigo, String dni, String nombreCliente, int cantidad, String descripcion, double precioUnitario) {
        this.codigo = codigo;
        this.fecha = new Date();
        this.dni = dni;
        this.nombreCliente = nombreCliente;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.importeTotal = cantidad * precioUnitario;
    }

    public Boleta(String codigo, Date fecha, String dni, String nombreCliente, List<DetalleBoleta> detalleBoletaList) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.dni = dni;
        this.nombreCliente = nombreCliente;
        this.detalleBoletaList = detalleBoletaList;
    }

    public String getCodigo(){
        return codigo;
    }
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public Date getFecha(){
        return fecha;
    }

    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
    public String getDni(){
        return dni;
    }

    public void setDni(String dni){
        this.dni = dni;
    }
    public String getNombreCliente(){
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente){
        this.nombreCliente = nombreCliente;
    }
    public int getCantidad(){
        return cantidad;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario(){
        return precioUnitario;
    }
    public void setPrecioUnitario(double precioUnitario){
        this.precioUnitario = precioUnitario;
    }

    public double getImporteTotal(){
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal){
        this.importeTotal = importeTotal;
    }

    public List<DetalleBoleta> getDetalleBoletaList() {
        return detalleBoletaList;
    }

    public void setDetalleBoletaList(List<DetalleBoleta> detalleBoletaList) {
        this.detalleBoletaList = detalleBoletaList;
    }
}

