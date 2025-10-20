package sv.uees.inventory_management.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import sv.uees.inventory_management.model.entity.UserEntity;
import sv.uees.inventory_management.service.UserService;
// import sv.uees.inventory_management.utils.DatabaseStatusChecker; // Clase pendiente de implementación

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Controlador que gestiona la interacción de la vista de inicio de sesión (LoginView.fxml).
 * Es responsable de capturar las credenciales, llamar al servicio y gestionar el flujo de la UI.
 */
public class LoginController {

    // Componentes FXML
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label errorText;

    // Servicios (Instancia directa, sin DI)
    private final UserService userService = new UserService();

    @FXML
    public void initialize() {
        // Lógica de inicialización (ej. verificar la conexión a la BD)
        // DatabaseStatusChecker.showAlertIfDisconnected();
        // DatabaseStatusChecker.printStatus();
    }

    // --- Punto de entrada para el inicio de sesión ---
    @FXML
    protected void onLoginButtonClick() {
        clearError();

        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Por favor, complete todos los campos.");
            return;
        }

        try {
            // Llama al método authenticate del UserService
            Optional<UserEntity> authenticatedUser = userService.authenticate(username, password);

            if (authenticatedUser.isPresent()) {
                // Éxito: Usuario autenticado
                UserEntity user = authenticatedUser.get();
                System.out.println("Usuario autenticado: " + user.getUsername() + ", Rol: " + user.getRole());

                clearFields();

                try {
                    changeToDashboardScene();
                } catch (IOException ioException) {
                    showError("Error crítico: No se pudo cargar la vista del Panel Principal.");
                }
            } else {
                // Fallo: Credenciales incorrectas
                showError("Credenciales incorrectas. Verifique su usuario y contraseña.");
                clearFields();
            }
        } catch (SQLException e) {
            // Error de conexión o SQL
            System.err.println("Error de base de datos durante el login: " + e.getMessage());
            showError("Error de conexión al sistema. Contacte al administrador.");
        } catch (Exception e) {
            // Otros errores inesperados
            e.printStackTrace();
            showError("Ocurrió un error inesperado. Contacte al soporte técnico.");
        }
    }

    // --- Punto de entrada para el registro de usuario ---
    /**
     * Gestiona el evento de clic en el enlace de "Registrar Usuario" en el FXML.
     */
    @FXML
    protected void onRegisterLinkClick() {
        clearError();
        clearFields();
        try {
            changeToRegisterScene();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            showError("Error crítico: No se pudo cargar la vista de Registro.");
        }
    }

    // --- Lógica de Cambio de Escena ---
    private void changeToDashboardScene() throws IOException {
        Stage currentStage = (Stage) txtUsername.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/view/DashboardView.fxml")
        );
        Scene dashboardScene = new Scene(fxmlLoader.load());

        currentStage.setScene(dashboardScene);
        currentStage.setTitle("");
        currentStage.centerOnScreen();
    }

    private void changeToRegisterScene() throws IOException {
        Stage currentStage = (Stage) txtUsername.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/view/RegisterView.fxml")
        );
        Scene registerScene = new Scene(fxmlLoader.load());

        currentStage.setScene(registerScene);
        currentStage.setTitle("");
        currentStage.show();
    }

    // --- Métodos Auxiliares ---
    private void clearFields() {
        txtUsername.clear();
        txtPassword.clear();
        txtUsername.requestFocus();
    }

    private void showError(String message) {
        errorText.setText(message);
    }

    private void clearError() {
        errorText.setText("");
    }

    @FXML
    private void onFieldClick() {
        clearError();
    }
}