package sv.uees.inventory_management.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sv.uees.inventory_management.service.LoginService;
import sv.uees.inventory_management.utils.DatabaseStatusChecker;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label errorText;

    private final LoginService loginService = new LoginService();

    @FXML
    public void initialize() {
        // Alerta gráfica si falla la conexión
        DatabaseStatusChecker.showStatusAlert();
        // Solo imprime en consola el estado de la base
        DatabaseStatusChecker.printStatus();
    }


    @FXML
    protected void onLoginButtonClick() {
        errorText.setText("");

        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showErrorMessage("Por favor, complete todos los campos");
            return;
        }

        try {
            boolean authenticated = loginService.authenticateUser(username, password);

            if (authenticated) {
                showAlertMessage("Inicio de sesión exitoso", "Bienvenido al sistema, " + username);
                clearFields();
                // TODO: Cambiar escena al dashboard
            } else {
                showErrorMessage("Credenciales incorrectas");
                clearFields();
            }

        } catch (Exception e) {
            // Alerta gráfica si falla la conexión o cualquier error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de conexión");
            alert.setHeaderText("No se pudo conectar a la base de datos");
            alert.setContentText("Por favor, contacte al soporte técnico.");
            alert.showAndWait();

            e.printStackTrace(); // útil solo en desarrollo
        }
    }

    private void clearFields() {
        txtPassword.clear();
        txtUsername.requestFocus();
    }

    private void showErrorMessage(String message) {
        errorText.setText(message);
    }

    private void showAlertMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onFieldClick() {
        errorText.setText("");
    }
}
