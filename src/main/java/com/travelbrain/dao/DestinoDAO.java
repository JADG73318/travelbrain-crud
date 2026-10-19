package com.travelbrain.dao;

import com.travelbrain.modelo.Destino;
import com.travelbrain.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Acceso a datos de la tabla destino (patron DAO).
 * Implementa las cuatro operaciones del CRUD con sentencias preparadas.
 */
public class DestinoDAO {

    private static final String SQL_INSERTAR =
            "INSERT INTO destino (nombre, pais, descripcion, mejor_epoca) VALUES (?, ?, ?, ?)";
    private static final String SQL_LISTAR =
            "SELECT id, nombre, pais, descripcion, mejor_epoca FROM destino ORDER BY id";
    private static final String SQL_BUSCAR =
            "SELECT id, nombre, pais, descripcion, mejor_epoca FROM destino WHERE id = ?";
    private static final String SQL_ACTUALIZAR =
            "UPDATE destino SET nombre = ?, pais = ?, descripcion = ?, mejor_epoca = ? WHERE id = ?";
    private static final String SQL_ELIMINAR =
            "DELETE FROM destino WHERE id = ?";

    /** CREATE: registra un destino y devuelve el id generado. */
    public int insertar(Destino destino) throws SQLException {
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(SQL_INSERTAR, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, destino.getNombre());
            ps.setString(2, destino.getPais());
            ps.setString(3, destino.getDescripcion());
            ps.setString(4, destino.getMejorEpoca());
            ps.executeUpdate();

            try (ResultSet claves = ps.getGeneratedKeys()) {
                return claves.next() ? claves.getInt(1) : -1;
            }
        }
    }

    /** READ: devuelve todos los destinos. */
    public List<Destino> listar() throws SQLException {
        List<Destino> destinos = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(SQL_LISTAR);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                destinos.add(mapear(rs));
            }
        }
        return destinos;
    }

    /** READ: busca un destino por id; devuelve null si no existe. */
    public Destino buscarPorId(int id) throws SQLException {
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(SQL_BUSCAR)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        }
    }

    /** UPDATE: modifica un destino existente; devuelve true si se actualizo. */
    public boolean actualizar(Destino destino) throws SQLException {
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(SQL_ACTUALIZAR)) {
            ps.setString(1, destino.getNombre());
            ps.setString(2, destino.getPais());
            ps.setString(3, destino.getDescripcion());
            ps.setString(4, destino.getMejorEpoca());
            ps.setInt(5, destino.getId());
            return ps.executeUpdate() > 0;
        }
    }

    /** DELETE: elimina un destino por id; devuelve true si se elimino. */
    public boolean eliminar(int id) throws SQLException {
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(SQL_ELIMINAR)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Destino mapear(ResultSet rs) throws SQLException {
        return new Destino(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("pais"),
                rs.getString("descripcion"),
                rs.getString("mejor_epoca"));
    }
}
