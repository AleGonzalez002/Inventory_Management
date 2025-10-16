package sv.uees.inventory_management.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sv.uees.inventory_management.service.LoginService;
import sv.uees.inventory_management.utils.DatabaseStatusChecker;

public class LoginController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label errorText;

    private final LoginService loginService = new LoginService();

    @FXML
    public void initialize() {
        // Verifica la conexión al iniciar
        DatabaseStatusChecker.showAlertIfDisconnected();
        DatabaseStatusChecker.printStatus();
    }

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
                //Cambiar escena al dashboard desde aqui pls
            } else {
                showError("Credenciales incorrectas");
                clearFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showError("Ocurrió un error inesperado. Contacte al soporte técnico.");
        }
    }

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