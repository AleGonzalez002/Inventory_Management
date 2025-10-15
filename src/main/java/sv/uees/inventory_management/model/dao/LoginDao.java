package sv.uees.inventory_management.model.dao;

import sv.uees.inventory_management.model.entity.LoginEntity;
import sv.uees.inventory_management.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public LoginEntity validateUser(String username, String password) throws SQLException {
        String query = "SELECT id_usuario, nombre, usuario, rol, fecha_registro " +
                "FROM usuarios " +
                "WHERE usuario = ? AND contrasena = ?"; // username EXACTO, password EXACTO

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username); // NO toLowerCase()
            statement.setString(2, password); // Case-sensitive

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    LoginEntity user = new LoginEntity();
                    user.setIdUsuario(rs.getInt("id_usuario"));
                    user.setNombre(rs.getString("nombre"));
                    user.setUsuario(rs.getString("usuario"));
                    user.setRol(rs.getString("rol"));
                    user.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                    return user;
                }
            }
        }
        return null; // usuario o contraseña incorrectos
    }
}