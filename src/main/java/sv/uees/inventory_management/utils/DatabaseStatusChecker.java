package sv.uees.inventory_management.utils;

import javafx.scene.control.Alert;
import java.sql.Connection;

/**
 * Clase para verificar el estado de la conexión con la base de datos.
 * Muestra alertas simples si ocurre un error de conexión.
 */
public class DatabaseStatusChecker {

    /**
     * Verifica si hay conexión activa con la base de datos.
     *
     * @return true si la conexión fue exitosa, false si falló.
     */
    public static boolean isConnected() {
        try (Connection connection = DatabaseConnection.connect()) {
            return connection != null && !connection.isClosed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Muestra una alerta simple si no hay conexión con la base de datos.
     */
    public static void showAlertIfDisconnected() {
        if (!isConnected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle(null);
            alert.setContentText("No se pudo conectar a la base de datos.");
            alert.showAndWait();
        }
    }


    /**
     * Imprime el estado de la conexión en la consola (para depuración).
     */
    public static void printStatus() {
        if (isConnected()) {
            System.out.println("Conexion establecida correctamente.");
        } else {
            System.err.println("No se pudo conectar a la base de datos.");
        }
    }
}
