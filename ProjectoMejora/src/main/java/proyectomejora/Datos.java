package main.java.proyectomejora;

import java.util.ArrayList;

public class Datos {
// VERIFICACION
    public ArrayList<Producto> listaProductos = new ArrayList<>();
    public ArrayList<Boleta> listaBoletas = new ArrayList<>();

    public void agregarBoleta(Boleta boleta) {
        listaBoletas.add(boleta);
    }

    public ArrayList<Boleta> getListaBoletas() {
        return listaBoletas;
    }

    public void setListaBoletas(ArrayList<Boleta> listaBoletas) {
        this.listaBoletas = listaBoletas;
    }

    public void agregarZapato(Producto zapato) {
        listaProductos.add(zapato);
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public boolean eliminarZapato(int codigo) {
        return listaProductos.removeIf(producto -> producto.getCodigo() == codigo);
    }

    public Producto consultarZapato(String nombre) {
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }


}

