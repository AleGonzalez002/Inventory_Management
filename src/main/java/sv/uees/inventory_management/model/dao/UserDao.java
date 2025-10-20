package sv.uees.inventory_management.model.dao;

import sv.uees.inventory_management.model.entity.UserEntity;
// Usaremos la versión simplificada de conexión que no requiere inyección de dependencias.
import sv.uees.inventory_management.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Necesario para obtener la clave generada
import java.util.Optional; // Uso Optional para manejo profesional de resultados nulos

/**
 * Clase de Acceso a Datos (DAO) que gestiona la persistencia de la entidad UserEntity.
 * Esta implementación utiliza JDBC vanilla y el método estático de conexión.
 */
public class UserDao {

    // Consulta para validar las credenciales del usuario (LOGIN)
    private static final String QUERY_AUTHENTICATE =
            "SELECT id_usuario, nombre, usuario, rol, fecha_registro FROM usuarios WHERE usuario = ? AND contrasena = ?";

    // Consulta para insertar un nuevo usuario (REGISTRO)
    // Nota: 'contrasena' debe ser la contraseña sin hashear en esta capa, por simplicidad.
    private static final String QUERY_INSERT_USER =
            "INSERT INTO usuarios (nombre, usuario, contrasena, rol, fecha_registro) VALUES (?, ?, ?, ?, NOW())";


    /**
     * Valida las credenciales de un usuario. (LOGIN)
     * @param username El nombre de usuario.
     * @param password La contraseña.
     * @return Optional que contiene el UserEntity si el login es exitoso.
     */
    public Optional<UserEntity> authenticateUser(String username, String password) {
        // Usamos el método estático de tu clase de conexión
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(QUERY_AUTHENTICATE)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Si se encuentra, mapeamos y devolvemos el objeto envuelto en Optional.
                    return Optional.of(mapToUserEntity(rs));
                }
            }
        } catch (SQLException e) {
            // Error de conexión o SQL. En un proyecto real, usaríamos Log4j aquí.
            System.err.println("Error de DAO al autenticar usuario: " + e.getMessage());
        }
        return Optional.empty(); // Falla el login o hay error.
    }

    /**
     * Registra un nuevo usuario en la base de datos. (REGISTRO)
     * @param user El objeto UserEntity con los datos a registrar.
     * @return El UserEntity con el ID generado por la base de datos.
     */
    public UserEntity saveUser(UserEntity user) {
        // Usamos el método estático de tu clase de conexión
        try (Connection connection = DatabaseConnection.connect();
             // Le decimos a JDBC que queremos la clave generada (id_usuario)
             PreparedStatement stmt = connection.prepareStatement(QUERY_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Obtener el ID generado automáticamente por MySQL
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setUserId(generatedKeys.getInt(1));
                    }
                }
                // Limpiar la contraseña antes de devolver el objeto
                user.setPassword(null);
            }

        } catch (SQLException e) {
            System.err.println("Error de DAO al registrar usuario: " + e.getMessage());
            // En caso de error (ej: usuario duplicado), puedes devolver null o un objeto especial.
            return null;
        }
        return user;
    }


    /**
     * Mapea un ResultSet a un objeto UserEntity.
     */
    private UserEntity mapToUserEntity(ResultSet rs) throws SQLException {
        UserEntity user = new UserEntity();
        user.setUserId(rs.getInt("id_usuario"));
        user.setName(rs.getString("nombre"));
        user.setUsername(rs.getString("usuario"));
        user.setRole(rs.getString("rol"));
        user.setRegistrationDate(rs.getTimestamp("fecha_registro"));
        // NOTA: La contraseña nunca se mapea de vuelta del SELECT por seguridad.
        return user;
    }
}