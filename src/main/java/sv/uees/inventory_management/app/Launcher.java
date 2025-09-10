package sv.uees.inventory_management.app;

import sv.uees.inventory_management.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class Launcher {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DatabaseConnection.connect();
            if (connection != null) {
                System.out.println("Database connection successful.");
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error: Failed to connect to the database.");
                }
            }
        }
    }
}