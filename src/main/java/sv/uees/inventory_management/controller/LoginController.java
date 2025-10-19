package sv.uees.inventory_management.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sv.uees.inventory_management.service.LoginService;
import sv.uees.inventory_management.utils.DatabaseStatusChecker;

import java.io.IOException;

public class LoginController {

    // Componentes FXML
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label errorText;

    // Servicios
    private final LoginService loginService = new LoginService();

    @FXML
    public void initialize() {
        // Verifica la conexión al iniciar
        DatabaseStatusChecker.showAlertIfDisconnected();
        DatabaseStatusChecker.printStatus();
    }

    // --- Punto de entrada para el inicio de sesión ---
    @FXML
    protected void onLoginButtonClick() {
        clearError();

        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Por favor, complete todos los campos");
            return;
        }

        try {
            boolean authenticated = loginService.authenticateUser(username, password);
            if (authenticated) {
                clearFields();

                try {
                    changeToDashboardScene();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    showError("Error crítico: No se pudo cargar la vista del Dashboard.");
                }
            } else {
                showError("Credenciales incorrectas");
                clearFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showError("Ocurrió un error inesperado al autenticar. Contacte al soporte técnico.");
        }
    }

    // --- Lógica de Cambio de Escena (Dashboard) ---
    private void changeToDashboardScene() throws IOException {
        // 1. Obtiene la referencia a la ventana (Stage) actual usando un componente
        Stage currentStage = (Stage) txtUsername.getScene().getWindow();

        // 2. Carga el FXML de la nueva vista (DashboardView.fxml)
        // La ruta debe ser relativa al classpath de tu proyecto.
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/view/DashboardView.fxml")
        );
        Scene dashboardScene = new Scene(fxmlLoader.load());

        // 3. Establece la nueva escena y muestra la ventana
        currentStage.setScene(dashboardScene);
        currentStage.setTitle("Inventario - Dashboard Principal");
        currentStage.show();
    }
    // ----------------------------------------------

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