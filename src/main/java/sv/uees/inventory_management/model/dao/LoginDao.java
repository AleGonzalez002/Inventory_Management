package sv.uees.inventory_management.model.dao;

import sv.uees.inventory_management.model.entity.LoginEntity;
import sv.uees.inventory_management.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

    public LoginEntity validarUsuario(String usuario, String contrasena) {
        String sql = "SELECT id_usuario, usuario, contrasena FROM usuarios WHERE usuario = ? AND contrasena = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario);
            pstmt.setString(2, contrasena);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                LoginEntity Usuario = new LoginEntity();
                Usuario.setId(rs.getInt("id_usuario"));
                Usuario.setUsuario(rs.getString("usuario"));
                return Usuario;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}