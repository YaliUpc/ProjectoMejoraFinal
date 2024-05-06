package main.java.proyectomejora;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Datos {

    public ArrayList<Producto> listaProductos = new ArrayList<>();
    public ArrayList<Boleta> listaBoletas = new ArrayList<>();
    public List<DetalleBoleta> listDetalleBol = new ArrayList<>();

    public Datos precargarDatos(){
        Datos data = new Datos();
        ArrayList<Producto> listaProductos = new ArrayList<>();
        ArrayList<Boleta> listaBoletas = new ArrayList<>();

        Producto producto1 = new Producto(100,"ZAPATILLA DEPORTIVA","BLANCO",40,490.00,222);
        listaProductos.add(producto1);
        Producto producto2 = new Producto(101,"ZAPATILLA DEPORTIVA","BLANCO",41,490.00,222);
        listaProductos.add(producto2);
        Producto producto3 = new Producto(102,"ZAPATILLA DEPORTIVA","BLANCO",42,490.00,222);
        listaProductos.add(producto3);
        Producto producto4 = new Producto(103,"ZAPATILLA DEPORTIVA","GRIS",40,490.00,222);
        listaProductos.add(producto4);
        Producto producto5 = new Producto(104,"ZAPATILLA DEPORTIVA","GRIS",41,490.00,222);
        listaProductos.add(producto5);
        Producto producto6 = new Producto(105,"ZAPATILLA DEPORTIVA","GRIS",42,490.00,222);
        listaProductos.add(producto6);
        Producto producto7 = new Producto(106,"ZAPATO CAP TOE OXFORD","MARRON",40,690.00,222);
        listaProductos.add(producto7);
        Producto producto8 = new Producto(107,"ZAPATO CAP TOE OXFORD","MARRON",41,690.00,222);
        listaProductos.add(producto8);
        Producto producto9 = new Producto(108,"ZAPATO CAP TOE OXFORD","MARRON",42,690.00,222);
        listaProductos.add(producto9);
        Producto producto10 = new Producto(109,"MOCASIN FORMAL","NEGRO",40,690.00,222);
        listaProductos.add(producto10);
        Producto producto11 = new Producto(110,"MOCASIN FORMAL","NEGRO",41,690.00,222);
        listaProductos.add(producto11);
        Producto producto12 = new Producto(111,"MOCASIN","NEGRO",42,690.00,222);
        listaProductos.add(producto12);
        Producto producto13 = new Producto(112,"ZAPATO CASUAL","GRIS",40,689.00,222);
        listaProductos.add(producto13);
        Producto producto14 = new Producto(113,"ZAPATO CASUAL","GRIS",41,689.00,222);
        listaProductos.add(producto14);
        Producto producto15 = new Producto(114,"ZAPATO CASUAL","GRIS",42,689.00,222);
        listaProductos.add(producto15);
        Producto producto16 = new Producto(115,"ZAPATILLA CASUAL","NEGRO",40,689.00,222);
        listaProductos.add(producto16);
        Producto producto17 = new Producto(116,"ZAPATILLA CASUAL","NEGRO",41,689.00,222);
        listaProductos.add(producto17);
        Producto producto18 = new Producto(117,"ZAPATILLA CASUAL","NEGRO",42,689.00,222);
        listaProductos.add(producto18);


        int cantidadDeProductos = listaProductos.size();
        System.out.println("Cantidad de productos: " + cantidadDeProductos);

        String fechaString = "3/05/2024";
        SimpleDateFormat fechaFormateada = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date fecha = fechaFormateada.parse(fechaString);
            DetalleBoleta detalleBol = new DetalleBoleta(5,"ZAPATILLA CASUAL".concat(" / ").concat("NEGRO"), 128.00);
            listDetalleBol.add(detalleBol);
            detalleBol = new DetalleBoleta(2,"ZAPATILLA CASUAL".concat(" / ").concat("GRIS"), 128.00);
            listDetalleBol.add(detalleBol);

            Boleta boleta = new Boleta("1000",fecha,"74162527","MARTIN ALEXIS CAJO VELIT", listDetalleBol);
            listaBoletas.add(boleta);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        data.setListaProductos(listaProductos);
        data.setListaBoletas(listaBoletas);

        return data;
    }
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

    public Producto consultarProducto(int codigo) {
        for (Producto producto : listaProductos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    public int calcularTotalStock() {
        int totalStock = 0;
        for (Producto producto : listaProductos) {
            totalStock += producto.getStock();
        }
        return totalStock;
    }


}

