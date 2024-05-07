package main.java.proyectomejora;//SE IMPORTAN TODAS LAS CLASES NECESARIAS
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//SE CREA UNA CLASE PÚBLICA PARA LA FUNCIONALIDAD
public class Banner {
    //SE DEFINE EL SCANNER QUE SERVIRÁ PARA TOMAR ENTRADAS DEL USUARIO
    private final Scanner sc = new Scanner(System.in);
    // SE DEFINE UNA VARIABLE datos QUE CARGA POR COMPLETO EL CONTENIDO DE LA CLASE Datos
    private static Datos datos = new Datos();

    static {
        datos = datos.precargarDatos();
    }
    private int opcionParaSeguir;

    //SE CREA UN MÉTODO PUBLICO PARA MOSTRAR EL MENÚ Y QUE SERÁ REUTILIZABLE
    public void mostrarMenu() {
        //SE DEFINE UNA ESTRUCTURA REPETITIVA (BUCLE) PARA DIBUJAR DE FORMA REPETIDA LA CONSOLA
        do {
            System.out.println("┌───────────────────────────────────────────┐");
            System.out.println("│           "+"SISTEMA - ZAPATAH"+"               │");
            System.out.println("│            MENÚ PRINCIPAL                 │");
            System.out.println("├───────────────────────────────────────────┤");
            System.out.println("│ 1. Ingreso de Nuevo Stock                 │");
            System.out.println("│ 2. Eliminar  Producto del Inventario      │");
            System.out.println("│ 3. Consultar Por Código de producto       │");
            System.out.println("│ 4. Actualizar Stock                       │");
            System.out.println("│ 5. Consultar Boleta emitida, Por DNI      │");
            System.out.println("│ 6. Mostrar Todo el Inventario             │");
            System.out.println("│ 7. Salir                                  │");
            System.out.println("└───────────────────────────────────────────┘");

            while (true) {
                System.out.print("Elija una opción:");
                if (sc.hasNextInt()) {
                    //opcionParaSeguir es la variable donde se guarda la opción que ingrese el usuario
                    opcionParaSeguir = sc.nextInt();
                    if (opcionParaSeguir >= 1 && opcionParaSeguir <= 7) {
                        sc.nextLine();
                        break; // ROMPER BUCLE SI INGRESA UN NÚMERO VÁLIDO
                    } else {
                        System.out.println("Ingrese una opción válida");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, ingrese solo números enteros del 1 al 7.");
                    sc.next(); //
                }
            }

            //SE DEFINEN LOS CASOS DE USO
            switch (opcionParaSeguir) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    eliminarProducto();
                    break;
                case 3:
                    consultarProducto();
                    break;
                case 4:
                    actualizarProducto();
                    break;
                case 5:
                    consultarCliente();
                    break;
                case 6:
                    imprimirInventario();
                    break;
                case 7:
                    //SE SALE DEL PROGRAMA
                    System.out.println("Saliendo del Programa");
                    break;
                default:
                    System.out.println("Elija una opción válida");
                    break;
            }
        } while (opcionParaSeguir != 7);
    }

    //MÉTODO PARA INSERTAR UN NUEVO OBJETO DENTRO DE LA ListaDeProductos
    private void registrarProducto() {
        String modelo, color;
        double precio;
        int talla, cantidad;
        System.out.println("Ingrese detalles del zapato: ");
        int codigo = datos.getListaProductos().getLast().getCodigo()+1;
        System.out.print("Modelo: ");
        modelo = sc.nextLine();
        color = validarColor();
        talla = validarTalla();
        System.out.print("Precio: ");
        precio = sc.nextDouble();
        sc.nextLine();
        System.out.print("Cantidad: ");
        cantidad = sc.nextInt();
        sc.nextLine();

        Producto nuevoProducto = new Producto(codigo, modelo, color, talla, precio, cantidad);
        datos.agregarZapato(nuevoProducto);

        eleccionCerrar("PRODUCTO REGISTRADO CON ÉXITO\nElija una opción: \n1.VOLVER AL MENÚ PRINCIPAL\n2.SALIR DEL PROGRAMA");
    }

    private int validarTalla() {
        int talla;
        while (true) {
            System.out.print("Talla: ");
            if (sc.hasNextInt()) {
                talla = sc.nextInt();
                sc.nextLine();
                if (talla >= 38 && talla <= 42) {
                    break;
                } else {
                    System.out.println("TIENE QUE INGRESAR UNA TALLA DENTRO DEL RANGO: 38 - 42");
                }
            } else {
                System.out.println("Por favor, ingrese un número entero válido para la talla.");
                sc.nextLine();
            }
        }
        return talla;
    }

    private void eliminarProducto() {
        boolean eliminado;
        do {
            System.out.print("Ingresa un código: ");
            while (!sc.hasNextInt()) {
                System.out.println("El código debe ser numérico, intente de nuevo:");
                sc.next();
            }
            int codigo = sc.nextInt();
            sc.nextLine();
            eliminado = datos.eliminarZapato(codigo);
            if (!eliminado) {
                System.out.println("No se encontró el producto, intente de nuevo");
            }
        } while (!eliminado);
        eleccionCerrar("PRODUCTO ELIMINADO DEL INVENTARIO CON ÉXITO\nElija una opción: \n1.VOLVER AL MENÚ PRINCIPAL\n2.SALIR DEL PROGRAMA");
    }

    private void maquetaDetalle(Producto producto){
        System.out.println("┌───────────────────────────────────────────────┐");
        System.out.println("|               DETALLE DE PRODUCTO             |");
        System.out.println(" ───────────────────────────────────────────────");
        System.out.println("|       MODELO | " + completarEspacios(producto.getModelo(), 31) + "|");
        System.out.println("|        COLOR | " + completarEspacios(producto.getColor(), 31) + "|");
        System.out.println("|        TALLA | " + completarEspacios(String.valueOf(producto.getTalla()), 31) + "|");
        System.out.println("|       PRECIO | " + completarEspacios(String.valueOf(producto.getPrecio()), 31) + "|");
        System.out.println("|        CANT. | " + completarEspacios(String.valueOf(producto.getStock()), 31) + "|");
        System.out.println("|  Desea vender? DIGITE UNA OPCIÓN:             | ");
        System.out.println("|  1. SÍ         2.NO                           | ");
        System.out.println("└───────────────────────────────────────────────┘");
    }

    private void consultarProducto() {
        List<DetalleBoleta> listaDetalleBoleta = new ArrayList<>();
        System.out.print("Ingresa codigo del Producto: ");
        int codigo = sc.nextInt();
        int cantidadAVender;
        Producto zapato = datos.consultarProducto(codigo);
        if (zapato != null) {
            maquetaDetalle(zapato);
            int eleccionVenta = sc.nextInt();
            String nombreCliente;
            String dni;
            String codigoBoleta = datos.getListaBoletas().getLast().getCodigo();
            Date fechaActual = new Date();
            boolean vendiendo;
            switch (eleccionVenta) {
                case 1:
                    do {
                        System.out.print("Cantidad que desea vender:");
                        cantidadAVender = sc.nextInt();
                        sc.nextLine();

                        if (cantidadAVender > zapato.getStock()) {
                            System.out.println("NO HAY STOCK SUFICIENTE");
                        }else{
                            DetalleBoleta nuevoDetalleBoleta = new DetalleBoleta(cantidadAVender, zapato.getModelo(), zapato.getPrecio());
                            listaDetalleBoleta.add(nuevoDetalleBoleta);
                            zapato.reducirStock(cantidadAVender);
                        }

                        System.out.print("Desea continuar Vendiendo?\n1.SI\n2.NO\n");
                        int opcion = sc.nextInt();
                        sc.nextLine();
                        if (opcion == 1) {
                            System.out.print("Ingresa codigo del Producto: ");
                            codigo = sc.nextInt();
                            sc.nextLine();
                            zapato = datos.consultarProducto(codigo);
                            maquetaDetalle(zapato);
                            vendiendo = true;
                        }else{
                            vendiendo = false;
                        }
                    } while (vendiendo);

                    System.out.print("Ingrese el nombre completo del comprador: ");
                    nombreCliente = sc.nextLine();
                    dni = validarDNI();

                    //CREAR NUEVA BOLETA
                    Boleta nuevaBoleta = new Boleta(codigoBoleta + 1, fechaActual, dni, nombreCliente, listaDetalleBoleta);
                    datos.agregarBoleta(nuevaBoleta);
                    imprimirBoleta(nuevaBoleta);
                    eleccionCerrar("BOLETA IMPRESA CON ÉXITO\nElija una opción: \n1.VOLVER AL MENÚ PRINCIPAL\n2.SALIR DEL PROGRAMA");
                    break;
                case 2:
                    mostrarMenu();
                    break;
                default:
                    System.out.println("OPCIÓN INVÁLIDA: SALIENDO DEL PROGRAMA");
            }
        } else  {
            System.out.println("Producto no encontrado, elija una opción:\n1.Volver a intentarlo\n2.Salir del programa");
            int eleccion = sc.nextInt();
            switch (eleccion) {
                case 1:
                    consultarProducto();
                    break;
                case 2:
                    mostrarMenu();
                    break;
                default :
                    System.out.println("OPCIÓN INVÁLIDA: SALIENDO DEL PROGRAMA");
            }
        }
    }

    private void actualizarProducto() {
        int eleccionActualizacion;
        int codigoZapato;
        int nuevaTalla;
        String nuevoModelo, nuevoColor;
        double nuevoPrecio;
        int nuevaCantidad;
        System.out.println("Ingrese el codigo del zapato que va a actualizar: ");
        codigoZapato = sc.nextInt();
        sc.nextLine();
        Producto zapato = datos.consultarProducto(codigoZapato);
        if (zapato != null) {
            System.out.println("Desea actualizar:\n1.Modelo\n2.Color\n3.Talla\n4.Precio\n5.Cantidad\n6.Todos los detalles");
            eleccionActualizacion = sc.nextInt();
            switch (eleccionActualizacion) {
                case 1:
                    System.out.print("Nuevo modelo: ");
                    nuevoModelo = sc.next();
                    zapato.setModelo(nuevoModelo);
                    break;
                case 2:
                    System.out.print("Nuevo color: ");
                    nuevoColor = sc.next();
                    zapato.setColor(nuevoColor);
                    break;
                case 3:
                    System.out.print("Nueva talla: ");
                    nuevaTalla = sc.nextInt();
                    zapato.setTalla(nuevaTalla);
                    break;
                case 4:
                    System.out.print("Nuevo precio: ");
                    nuevoPrecio = sc.nextDouble();
                    zapato.setPrecio(nuevoPrecio);
                    break;
                case 5:
                    System.out.print("Nuevo stock: ");
                    nuevaCantidad = sc.nextInt();
                    zapato.setStock(nuevaCantidad);
                    break;
                case 6:
                    System.out.println("Ingrese los nuevos detalles del zapato:");
                    nuevoModelo = sc.next();
                    nuevoColor = validarColor();
                    nuevaTalla = validarTalla();
                    System.out.print("Nuevo precio: ");
                    nuevoPrecio = sc.nextDouble();
                    System.out.print("Nueva cantidad: ");
                    nuevaCantidad = sc.nextInt();

                    zapato.setModelo(nuevoModelo);
                    zapato.setColor(nuevoColor);
                    zapato.setTalla(nuevaTalla);
                    zapato.setPrecio(nuevoPrecio);
                    zapato.setStock(nuevaCantidad);
                default:
                    System.out.println("Ingrese una opción válida");
            }
            eleccionCerrar("PRODUCTO ACTUALIZADO CON ÉXITO\nElija una opción: \n1.VOLVER AL MENÚ PRINCIPAL\n2.SALIR DEL PROGRAMA");

        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void consultarCliente() {
        String dni = validarDNI();

        //BUSCA EL DNI DENTRO DE LA LISTA DE BOLETAS
        for(Boleta boleta: datos.getListaBoletas()){
            //VERIFICA SI EXISTE ESE DNI
            if(boleta.getDni().equals(dni)){
                //IMPRIME LA BOLETA
                imprimirBoleta(boleta);
            }
        }
    }

    private void imprimirInventario() {
        //OBTIENE LA LISTA DE PRODUCTOS
        ArrayList<Producto> listaProductos = datos.getListaProductos();
        //VERIFICA SI LA LISTA ESTÁ VACÍA
        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos para mostrar.");
        //SI NO ESTÁ VACÍA, DIBUJA LA CONSOLA
        } else {
            System.out.println("┌───────────────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("| COD | Modelo                         | Color                  | Talla | Precio | Cant |");
            System.out.println("└───────────────────────────────────────────────────────────────────────────────────────┘");
            //IMPRIME LOS DATOS
            for (Producto producto : listaProductos) {
                alineamientoCol(producto);
            }
            System.out.println("└───────────────────────────────────────────────────────────────────────────────────────┘");
            int cantidadListas = listaProductos.size();
            System.out.println("Tamaño de Inventario: "+cantidadListas+" | Total de Unidades en Stock:"+datos.calcularTotalStock());
        }
    }
    //MÉTODO PARA ALINEAR LAS COLUMNAS TOMANDO EL MÉTODO completarEspacios
    private void alineamientoCol(Producto producto){
        System.out.println(""
                .concat("| ").concat(completarEspacios(String.valueOf(producto.getCodigo()),4))
                .concat("| ").concat(completarEspacios(producto.getModelo(),25))
                .concat("| ").concat(completarEspacios(producto.getColor(),29))
                .concat("| ").concat(completarEspacios(String.valueOf(producto.getTalla()),6))
                .concat("| ").concat(completarEspacios(String.valueOf(producto.getPrecio()),7))
                .concat("| ").concat(completarEspacios(String.valueOf(producto.getStock()),5))
                .concat("|")
        );
    }

    private void imprimirBoleta(Boleta boleta){
        SimpleDateFormat fechaFormateada = new SimpleDateFormat("dd/MM/yyyy");
        String DDMMYYYY = fechaFormateada.format(boleta.getFecha());
        System.out.println(""
                .concat("┌─────────────────────────────────────────────────────────────────────────────────────────────────────┐\n")
                .concat("|    RUC:20603021640                                                                      "+DDMMYYYY+" |\n")
                .concat("|                                      EMPRESA DE ZAPATERIA ZAPATAH                                   |\n")
                .concat("|    BOLETA N° : "+completarEspacios(boleta.getCodigo(),85)+"|\n")
                .concat("|  DNI CLIENTE : "+completarEspacios(boleta.getDni(),85)+"|\n")
                .concat("|       NOMBRE : "+completarEspacios(boleta.getNombreCliente(),85)+"|\n")
                .concat("┌─────────────────────────────────────────────────────────────────────────────────────────────────────┐\n")
                .concat("| CANT | DESCRIPCION                                                                      | PRECIO U. |\n")
                .concat(" ────────────────────────────────────────────────────────────────────────────────────────────────────\n")
                .concat(detalleboleta(boleta.getDetalleBoletaList()))
        );
    }
    private String detalleboleta(List<DetalleBoleta> listDetBol){
        String producto = "";
        double totalpago = 0;
        for(DetalleBoleta dt: listDetBol){
            producto=producto
                    .concat("|  ").concat(completarEspacios(String.valueOf(dt.getCant()),4))
                    .concat("| ").concat(completarEspacios(dt.getModelo(),81))
                    .concat("| ").concat(completarEspacios(String.valueOf(dt.getPrecio()),10))
                    .concat("|\n");

            totalpago = totalpago+dt.getPrecio() * listDetBol.getLast().getCant();
        }

        producto = producto
                .concat("|                                                                                  TOTAL  | "+completarEspacios(String.valueOf(totalpago),10)+"|\n")
                .concat("|──────────────────────────────────────────────────────────────────────────────────────────────────────── ");

        return producto;
    }

    private String validarColor() {
        String nuevoColor;
        do {
            System.out.print("Nuevo Color: ");
            nuevoColor = sc.nextLine();
            if (!nuevoColor.matches("^\\D+$")) {
                System.out.println("EL COLOR NO PUEDE CONTENER UN NÚMERO, INTENTE DE NUEVO");
            }
        } while (!nuevoColor.matches("^\\D+$"));
        return nuevoColor;
    }

    private String validarDNI() {
        String dni;
        // Validación del DNI
        boolean dniValido;
        do {
            System.out.print("Ingrese el DNI del Comprador: ");
            dni = sc.nextLine();
            dniValido = dni.matches("\\d{8}"); // Verifica que sean 8 dígitos y que sean números
            if (!dniValido) {
                System.out.println("El DNI debe tener 8 dígitos numéricos, intente de nuevo.");
            }
        } while (!dniValido);
        return dni;
    }
//METODO QUE PERMITE ELEGIR Salir O VOLVER AL MENÚ1
    private void eleccionCerrar(String x) {
        System.out.println(x);
        boolean eleccionFinal = sc.nextInt() == 1;
        if (eleccionFinal) {
            mostrarMenu();
        } else {
            opcionParaSeguir = 7;
            System.out.println("Saliendo del programa");
        }
    }

    //MÉTODO PARA COMPLETAR LOS ESPACIOS AL DIBUJAR (TOMA DE VALORES el String y los espacios)
    private String completarEspacios(String palabra, int espacios){
        for(int i = palabra.length() ; i < espacios; i++){
            palabra=palabra.concat(" ");
        }
        return palabra;
    }
}
