package sv.uees.inventory_management.model.dao;

import sv.uees.inventory_management.model.entity.LoginEntity;
import sv.uees.inventory_management.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    private static final String QUERY_VALIDATE_USER =
            "SELECT id_usuario, nombre, usuario, rol, fecha_registro " + "FROM usuarios " + "WHERE usuario = ? AND contrasena = ?";

    //Valida un usuario en la base de datos
    public LoginEntity validateUser(String username, String password) throws SQLException {
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(QUERY_VALIDATE_USER)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapToLoginEntity(rs);
                }
            }
        }
        return null;
    }

    private LoginEntity mapToLoginEntity(ResultSet rs) throws SQLException {
        LoginEntity user = new LoginEntity();
        user.setIdUsuario(rs.getInt("id_usuario"));
        user.setNombre(rs.getString("nombre"));
        user.setUsuario(rs.getString("usuario"));
        user.setRol(rs.getString("rol"));
        user.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        return user;
    }
}