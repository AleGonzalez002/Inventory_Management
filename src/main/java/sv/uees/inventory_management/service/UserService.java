package sv.uees.inventory_management.service;

import sv.uees.inventory_management.model.dao.UserDao;
import sv.uees.inventory_management.model.entity.UserEntity;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Clase de Servicio que contiene la lógica de negocio para la gestión de usuarios.
 * Interactúa directamente con el DAO para la persistencia.
 */
public class UserService {

    // Instancia directa del DAO (sin inyección de dependencias por tu solicitud)
    private final UserDao userDao = new UserDao();

    /**
     * Autentica un usuario con username y password, aplicando reglas de negocio básicas.
     * @param username El nombre de usuario.
     * @param password La contraseña.
     * @return Un Optional con el UserEntity si la autenticación es exitosa, o vacío si falla.
     * @throws SQLException Si ocurre un error de base de datos durante el proceso.
     */
    public Optional<UserEntity> authenticate(String username, String password) throws SQLException {
        // 1. Lógica de Negocio: Validación de datos de entrada
        if (isNullOrEmpty(username) || isNullOrEmpty(password)) {
            // Devolvemos vacío si los datos son inválidos antes de tocar la BD
            return Optional.empty();
        }

        // 2. Acceso a Datos: Llamada al DAO
        // Usamos el método del DAO que maneja el acceso a la BD
        return userDao.authenticateUser(username, password);
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * Aquí iría la lógica de negocio como hashear la contraseña o validar unicidad.
     * @param user El objeto UserEntity a registrar.
     * @return El UserEntity guardado (con ID) o un Optional vacío si falla.
     */
    public Optional<UserEntity> registerUser(UserEntity user) {
        // 1. Lógica de Negocio: Validar datos
        if (user == null || isNullOrEmpty(user.getUsername()) || isNullOrEmpty(user.getPassword())) {
            return Optional.empty();
        }

        // **Lógica Pendiente:** Aquí iría el hasheo de la contraseña antes de enviarla al DAO.
        // user.setPassword(PasswordUtil.hash(user.getPassword()));

        // 2. Acceso a Datos: Llamada al DAO
        UserEntity registeredUser = userDao.saveUser(user);

        // El DAO devuelve null si hay un error (como usuario duplicado)
        return Optional.ofNullable(registeredUser);
    }


    /**
     * Valida si un string es nulo o está vacío después de recortar espacios.
     */
    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}