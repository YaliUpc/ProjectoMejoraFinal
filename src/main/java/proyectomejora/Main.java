package main.java.proyectomejora;

import java.util.Scanner;

public class Banner {
    private final Scanner scanner = new Scanner(System.in);
    private final Almacen almacen = new Almacen();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("┌─────────────────────────┐");
            System.out.println("│ BIENVENIDO A LA ZAPATERÍA │");
            System.out.println("├─────────────────────────┤");
            System.out.println("│ 1. Registrar Producto   │");
            System.out.println("│ 2. Eliminar Producto    │");
            System.out.println("│ 3. Consultar Producto   │");
            System.out.println("│ 4. Actualizar Producto  │");
            System.out.println("│ 5. Consultar por DNI    │");
            System.out.println("│ 6. Salir                │");
            System.out.println("└─────────────────────────┘");
            System.out.print("Digite su opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
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
                    consultarPorDNI();
                    break;
                case 6:
                    System.out.println("Gracias por usar el sistema. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 6);
    }

    private void registrarProducto() {
        // Implementación del método para registrar un producto
    }

    private void eliminarProducto() {
        // Implementación del método para eliminar un producto
    }

    private void consultarProducto() {
        // Implementación del método para consultar un producto
    }

    private void actualizarProducto() {
        // Implementación del método para actualizar un producto
    }

    private void consultarPorDNI() {
        // Implementación del método para consultar boletas por DNI
    }
}
