package sv.uees.inventory_management.utils;

import javafx.scene.control.Alert;
import java.sql.Connection;

public class DatabaseStatusChecker {

    public static boolean isDatabaseConnected() {
        try (Connection connection = DatabaseConnection.connect()) {
            return connection != null && !connection.isClosed();
        } catch (Exception e) {
            System.err.println("No se pudo establecer conexion con la base de datos: " + e.getMessage());
            return false;
        }
    }

    public static void printStatus() {
        if (isDatabaseConnected()) {
            System.out.println("Conexion a la base de datos establecida correctamente.");
        } else {
            System.err.println("Error: No se pudo conectar a la base de datos.");
        }
    }

    // Nuevo método para alerta gráfica
    public static void showStatusAlert() {
        if (!isDatabaseConnected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de conexion");
            alert.setHeaderText("No se pudo conectar a la base de datos");
            alert.showAndWait();
        }
    }
}
