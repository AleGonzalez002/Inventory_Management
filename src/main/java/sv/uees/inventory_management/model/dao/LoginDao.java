package sv.uees.inventory_management.model.dao;

import sv.uees.inventory_management.model.entity.LoginEntity;
import sv.uees.inventory_management.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

    public LoginEntity validarUsuario(String nombre, String contraseña) {
        String sql = "SELECT id, nombre, contraseña FROM usuarios WHERE nombre = ? AND contraseña = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, contraseña);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                LoginEntity usuario = new LoginEntity();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("nombre"));
                usuario.setNombre(rs.getString("nombre"));
                return usuario;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}