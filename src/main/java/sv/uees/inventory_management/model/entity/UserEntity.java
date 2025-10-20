package sv.uees.inventory_management.model.entity;

import java.sql.Timestamp;

/**
 * Entidad que representa la tabla 'usuarios' en la base de datos.
 * Contiene la estructura de datos (POJO) con getters y setters.
 */
public class UserEntity {

    // Mapea a id_usuario (Primary Key)
    private int userId;

    // Mapea a nombre
    private String name;

    // Mapea a usuario
    private String username;

    // Mapea a contrasena
    private String password;

    // Mapea a rol
    private String role;

    // Mapea a fecha_registro
    private Timestamp registrationDate;

    // --- Constructor ---

    /**
     * Constructor vacío.
     */
    public UserEntity() {
    }

    // --- Getters y Setters ---

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }
}