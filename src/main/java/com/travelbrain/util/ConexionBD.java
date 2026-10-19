package com.travelbrain.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Administra la conexion a MySQL mediante JDBC.
 * Las credenciales se leen de variables de entorno para no dejarlas en el codigo:
 *   TB_DB_URL, TB_DB_USER, TB_DB_PASSWORD
 */
public final class ConexionBD {

    private static final String URL_POR_DEFECTO =
            "jdbc:mysql://localhost:3306/travelbrain?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Bogota";
    private static final String USUARIO_POR_DEFECTO = "root";

    private ConexionBD() {
    }

    public static Connection obtenerConexion() throws SQLException {
        String url = leerVariable("TB_DB_URL", URL_POR_DEFECTO);
        String usuario = leerVariable("TB_DB_USER", USUARIO_POR_DEFECTO);
        String clave = leerVariable("TB_DB_PASSWORD", "");
        return DriverManager.getConnection(url, usuario, clave);
    }

    private static String leerVariable(String nombre, String valorPorDefecto) {
        String valor = System.getenv(nombre);
        return (valor == null || valor.isBlank()) ? valorPorDefecto : valor;
    }
}
