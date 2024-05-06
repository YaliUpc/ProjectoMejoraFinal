package main.java.proyectomejora;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Banner {
    private final Scanner sc = new Scanner(System.in);
    private static Datos datos = new Datos();

    public void mostrarMenu() {
        int opcion;
        datos = precargarDatos();
        do {
            System.out.println("┌───────────────────────────────────────────┐");
            System.out.println("│             MANEJO DE PRODUCTO            │");
            System.out.println("├───────────────────────────────────────────┤");
            System.out.println("│ 1. Registrar Zapato                       │");
            System.out.println("│ 2. Eliminar Zapato                        │");
            System.out.println("│ 3. Consultar Zapato                       │");
            System.out.println("│ 4. Actualizar                             │");
            System.out.println("│ 5. Consultar Cliente                      │");
            System.out.println("│ 6. Mostrar todo el inventario             │");
            System.out.println("│ 7. Salir                                  │");
            System.out.println("└───────────────────────────────────────────┘");
            System.out.print("Digite su opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    agregarZapato();
                    break;
                case 2:
                    eliminarZapato();
                    break;
                case 3:
                    consultarZapato();
                    break;
                case 4:
                    actualizarZapato();
                    break;
                case 5:
                    consultarCliente();
                    break;
                case 6:
                    imprimirInventario();
                    break;
                case 7:
                    System.out.println("Saliendo del Programa");
                    break;
                default:
                    System.out.println("Elija una opción válida");
                    break;
            }
        } while (opcion != 7);
    }

    private Datos precargarDatos(){
        Datos data = new Datos();
        ArrayList<Producto> listaProductos = new ArrayList<>();
        ArrayList<Boleta> listaBoletas = new ArrayList<>();

        Producto prod = new Producto(100,"GAME MASTER","AIR MAX","40",25.5,120);
        listaProductos.add(prod);
        prod = new Producto(101,"GAME MASTER","AIR MAX","35",25.5,120);
        listaProductos.add(prod);
        prod = new Producto(102,"GAME MASTER","AIR PREMIUM","20",25.5,120);
        listaProductos.add(prod);
        prod = new Producto(103,"GAME MASTER","AIR MAX","38",25.5,120);
        listaProductos.add(prod);

        String dateString = "14/02/2024";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(dateString);
            List<DetalleBoleta> listBol = new ArrayList<>();
            DetalleBoleta dtbol = new DetalleBoleta(5,"GAME MASTER".concat(" / ").concat("AIR MAX"), 127.5);
            listBol.add(dtbol);
            dtbol = new DetalleBoleta(2,"GAME MASTER".concat(" / ").concat("AIR PREMIUM"), 127.5);
            listBol.add(dtbol);

            Boleta boleta = new Boleta("1000",date,"74162527","MARTIN ALEXIS CAJO VELIT",listBol);
            listaBoletas.add(boleta);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        data.setListaProductos(listaProductos);
        data.setListaBoletas(listaBoletas);

        return data;
    }
    private void agregarZapato() {
        String nombreZapato, modelo, talla;
        double precio;
        int cantidad;
        System.out.println("Ingrese detalles del zapato: ");
        int codigo = datos.getListaProductos().getLast().getCodigo()+1;
        System.out.print("Nombre: ");
        nombreZapato = sc.nextLine();
        System.out.print("Modelo: ");
        modelo = sc.nextLine();
        System.out.print("Talla: ");
        talla = sc.nextLine();
        System.out.print("Precio: ");
        precio = sc.nextDouble();
        System.out.print("Cantidad: ");
        cantidad = sc.nextInt();

        Producto nuevoZapato = new Producto(codigo,nombreZapato, modelo, talla, precio, cantidad);
        datos.agregarZapato(nuevoZapato);
        System.out.println("zapato registrado de manera exitosa");
    }
    private void eliminarZapato() {
        System.out.print("Ingresa el codigo: ");
        int codigo = sc.nextInt();
        if (datos.eliminarZapato(codigo)) {
            System.out.println("Zapato eliminado");
        } else {
            System.out.println("Zapato no encontrado");
        }
    }
    private void consultarZapato() {
        System.out.print("Ingresa nombre de zapato: ");
        String nombre = sc.nextLine();
        Producto zapato = datos.consultarZapato(nombre);
        if (zapato != null) {
            System.out.println("┌───────────────────────────────────────────────┐");
            System.out.println("|               DETALLE DE ZAPATO               |");
            System.out.println(" ───────────────────────────────────────────────");
            System.out.println("|       NOMBRE | "+completarEspacios(zapato.getNombre(),31)+"|");
            System.out.println("|  DESCRIPCION | "+completarEspacios(zapato.getModelo(),31)+"|");
            System.out.println("|        TALLA | "+completarEspacios(zapato.getTalla(),31)+"|");
            System.out.println("|       PRECIO | "+completarEspacios(String.valueOf(zapato.getPrecio()),31)+"|");
            System.out.println("|        CANT. | "+completarEspacios(String.valueOf(zapato.getCantidad()),31)+"|");
            System.out.println("└───────────────────────────────────────────────┘");
        } else
            System.out.println("Zapato no encontrado.");
    }
    private void actualizarZapato() {
        int eleccionActualizacion;
        String nombreZapato,nuevoNombre, nuevoModelo, nuevaTalla;
        double nuevoPrecio;
        int nuevaCantidad;
        System.out.println("Ingrese el nombre del zapato que va a actualizar: ");
        nombreZapato = sc.next();
        Producto zapato = datos.consultarZapato(nombreZapato);
        if (zapato != null) {
            System.out.println("Desea actualizar:\n1.Nombre\n2.Modelo\n3.Talla\n4.Precio\n5.Cantidad\n6.Todos los detalles");
            eleccionActualizacion = sc.nextInt();
            switch (eleccionActualizacion) {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    nuevoNombre = sc.next();
                    zapato.setNombre(nuevoNombre);
                    break;
                case 2:
                    System.out.print("Nueva descripción: ");
                    nuevoModelo = sc.next();
                    zapato.setModelo(nuevoModelo);
                    break;
                case 3:
                    System.out.print("Nueva talla: ");
                    nuevaTalla = sc.next();
                    zapato.setTalla(nuevaTalla);
                    break;
                case 4:
                    System.out.print("Nuevo precio: ");
                    nuevoPrecio = sc.nextDouble();
                    zapato.setPrecio(nuevoPrecio);
                    break;
                case 5:
                    System.out.print("Nueva cantidad: ");
                    nuevaCantidad = sc.nextInt();
                    zapato.setCantidad(nuevaCantidad);
                    break;
                case 6:
                    System.out.println("Ingrese los nuevos detalles del zapato:");
                    System.out.print("Nueva descripción: ");
                    nuevoModelo = sc.next();
                    System.out.print("Nueva talla: ");
                    nuevaTalla = sc.next();
                    System.out.print("Nuevo precio: ");
                    nuevoPrecio = sc.nextDouble();
                    System.out.print("Nueva cantidad: ");
                    nuevaCantidad = sc.nextInt();

                    zapato.setModelo(nuevoModelo);
                    zapato.setTalla(nuevaTalla);
                    zapato.setPrecio(nuevoPrecio);
                    zapato.setCantidad(nuevaCantidad);
                default:
                    System.out.println("Ingrese una opción válida");
            }

            System.out.println("El zapato"+" ha sido actualizado");
        } else {
            System.out.println("Zapato no encontrado.");
        }
    }
    private void consultarCliente() {
        System.out.print("Ingrese DNI: ");
        String dni = sc.next();
        for(Boleta boleta: datos.getListaBoletas()){
            if(boleta.getDni().equals(dni)){
                imprimirBoleta(boleta);
            }
        }
    }
    private void imprimirInventario() {
        ArrayList<Producto> listaProductos = datos.getListaProductos();
        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos para mostrar.");
        } else {
            System.out.println("┌───────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("| COD | Nombre                   | Descripcion                  | Talla | Precio | Cant |");
            System.out.println("└───────────────────────────────────────────────────────────────────────────────────────┘");

            for (Producto producto : listaProductos) {
                alineamientoCol(producto);
            }
            System.out.println("└───────────────────────────────────────────────────────────────────────────────────────┘");
        }
    }
    private void alineamientoCol(Producto producto){
        System.out.println(""
                .concat("| ").concat(completarEspacios(String.valueOf(producto.getCodigo()),4))
                .concat("| ").concat(completarEspacios(producto.getNombre(),25))
                .concat("| ").concat(completarEspacios(producto.getModelo(),29))
                .concat("| ").concat(completarEspacios(producto.getTalla(),6))
                .concat("| ").concat(completarEspacios(String.valueOf(producto.getPrecio()),7))
                .concat("| ").concat(completarEspacios(String.valueOf(producto.getCantidad()),5))
                .concat("|")
        );
    }
    private void imprimirBoleta(Boleta boleta){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String DDMMYYYY = dateFormat.format(boleta.getFecha());
        System.out.println(""
                .concat("┌─────────────────────────────────────────────────────────────────────────────────────────────────────┐\n")
                .concat("|                                                                                          "+DDMMYYYY+" |\n")
                .concat("|                                      EMPRESA DE ZAPATERIA PREMIUM                                   |\n")
                .concat("|    BOLETA N° : "+completarEspacios(boleta.getCodigo(),85)+"|\n")
                .concat("|  DNI CLIENTE : "+completarEspacios(boleta.getDni(),85)+"|\n")
                .concat("|       NOMBRE : "+completarEspacios(boleta.getNombreCliente(),85)+"|\n")
                .concat("┌─────────────────────────────────────────────────────────────────────────────────────────────────────┐\n")
                .concat("| CANT | DESCRIPCION                                                                      | PRECIO U. |\n")
                .concat(" ─────────────────────────────────────────────────────────────────────────────────────────────────────\n")
                .concat(detalleboleta(boleta.getDetalleBoletaList()))
                .concat("└─────────────────────────────────────────────────────────────────────────────────────────────────────┘\n")
        );
    }
    private String detalleboleta(List<DetalleBoleta> listdtb){
        String producto = "";
        double totalpago = 0;
        for(DetalleBoleta dt: listdtb){
            producto=producto
                    .concat("|  ").concat(completarEspacios(String.valueOf(dt.getCant()),4))
                    .concat("| ").concat(completarEspacios(dt.getNombre(),81))
                    .concat("| ").concat(completarEspacios(String.valueOf(dt.getPrecio()),10))
                    .concat("|\n");

            totalpago = totalpago+dt.getPrecio();
        }

        producto = producto
                .concat(" ─────────────────────────────────────────────────────────────────────────────────────────────────────\n")
                .concat("|                                                                                  TOTAL  | "+completarEspacios(String.valueOf(totalpago),10)+"|\n");

        return producto;
    }
    private String completarEspacios(String palabra, int espacios){
        for(int i = palabra.length() ; i < espacios; i++){
            palabra=palabra.concat(" ");
        }
        return palabra;
    }
}
