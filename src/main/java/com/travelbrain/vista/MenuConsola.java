package com.travelbrain.vista;

import com.travelbrain.dao.DestinoDAO;
import com.travelbrain.modelo.Destino;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Menu de consola para gestionar los destinos de TravelBrain.
 */
public class MenuConsola {

    private final DestinoDAO destinoDAO = new DestinoDAO();
    private final Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");
            try {
                ejecutar(opcion);
            } catch (SQLException e) {
                manejarError(e);
            }
        } while (opcion != 0);
        System.out.println("Hasta pronto, gracias por usar TravelBrain.");
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("========== TRAVELBRAIN - DESTINOS ==========");
        System.out.println("1. Registrar destino");
        System.out.println("2. Listar destinos");
        System.out.println("3. Buscar destino por id");
        System.out.println("4. Actualizar destino");
        System.out.println("5. Eliminar destino");
        System.out.println("0. Salir");
    }

    private void ejecutar(int opcion) throws SQLException {
        switch (opcion) {
            case 1 -> registrar();
            case 2 -> listar();
            case 3 -> buscar();
            case 4 -> actualizar();
            case 5 -> eliminar();
            case 0 -> { }
            default -> System.out.println("Opcion no valida.");
        }
    }

    private void registrar() throws SQLException {
        String nombre = leerTextoObligatorio("Nombre: ");
        String pais = leerTextoObligatorio("Pais: ");
        String descripcion = leerTexto("Descripcion: ");
        String mejorEpoca = leerTexto("Mejor epoca para viajar: ");

        int id = destinoDAO.insertar(new Destino(nombre, pais, descripcion, mejorEpoca));
        System.out.println("Destino registrado con id " + id + ".");
    }

    private void listar() throws SQLException {
        List<Destino> destinos = destinoDAO.listar();
        if (destinos.isEmpty()) {
            System.out.println("No hay destinos registrados.");
            return;
        }
        destinos.forEach(System.out::println);
    }

    private void buscar() throws SQLException {
        int id = leerEntero("Id del destino: ");
        Destino destino = destinoDAO.buscarPorId(id);
        System.out.println(destino != null ? destino : "No existe un destino con id " + id + ".");
    }

    private void actualizar() throws SQLException {
        int id = leerEntero("Id del destino a actualizar: ");
        Destino actual = destinoDAO.buscarPorId(id);
        if (actual == null) {
            System.out.println("No existe un destino con id " + id + ".");
            return;
        }
        System.out.println("Actual: " + actual);
        System.out.println("(Deje vacio para conservar el valor actual)");

        actual.setNombre(leerTextoOpcional("Nombre", actual.getNombre()));
        actual.setPais(leerTextoOpcional("Pais", actual.getPais()));
        actual.setDescripcion(leerTextoOpcional("Descripcion", actual.getDescripcion()));
        actual.setMejorEpoca(leerTextoOpcional("Mejor epoca", actual.getMejorEpoca()));

        boolean ok = destinoDAO.actualizar(actual);
        System.out.println(ok ? "Destino actualizado." : "No se pudo actualizar el destino.");
    }

    private void eliminar() throws SQLException {
        int id = leerEntero("Id del destino a eliminar: ");
        String confirmacion = leerTexto("Confirma la eliminacion? (s/n): ");
        if (!confirmacion.equalsIgnoreCase("s")) {
            System.out.println("Eliminacion cancelada.");
            return;
        }
        boolean ok = destinoDAO.eliminar(id);
        System.out.println(ok ? "Destino eliminado." : "No existe un destino con id " + id + ".");
    }

    // ---------- Utilidades de lectura y manejo de errores ----------

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private String leerTextoObligatorio(String mensaje) {
        String texto;
        do {
            texto = leerTexto(mensaje);
            if (texto.isEmpty()) {
                System.out.println("Este campo es obligatorio.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    private String leerTextoOpcional(String campo, String valorActual) {
        String texto = leerTexto(campo + " [" + valorActual + "]: ");
        return texto.isEmpty() ? valorActual : texto;
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private void manejarError(SQLException e) {
        if (e.getErrorCode() == 1062) {
            System.out.println("Error: ya existe un destino con ese nombre en ese pais.");
        } else {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }
}
