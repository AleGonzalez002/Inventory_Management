package sv.uees.inventory_management.app;

import javafx.application.Application;
import sv.uees.inventory_management.utils.DatabaseConnection;
import java.sql.Connection;

public class Launcher {
    public static void main(String[] args) {
        // Primero probar la conexión a la base de datos
        if (probarConexionBD()) {
            // Si la conexión es exitosa, lanzar la aplicación JavaFX
            Application.launch(LoginApplication.class, args);
        } else {
            // Si falla la conexión, mostrar mensaje y salir
            System.err.println("No se pudo conectar a la base de datos. La aplicación se cerrará.");
            System.exit(1); // Salir con código de error
        }
    }

    private static boolean probarConexionBD() {
        System.out.println("Probando conexión a la base de datos...");
        try (Connection connection = DatabaseConnection.connect()) {
            System.out.println("Conexión a BD establecida correctamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return false;
        }
    }
}