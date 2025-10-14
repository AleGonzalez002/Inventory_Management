package sv.uees.inventory_management.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sv.uees.inventory_management.service.LoginService;
import sv.uees.inventory_management.utils.DatabaseConnection;
import java.sql.Connection;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label errorText;

    private final LoginService loginService = new LoginService();

    // ✅ Se ejecuta automáticamente al abrir el login.fxml
    @FXML
    public void initialize() {
        probarConexionBD();
    }

    private void probarConexionBD() {
        try (Connection connection = DatabaseConnection.connect()) {
            System.out.println("✅ Conexión a la base de datos establecida correctamente");
        } catch (Exception e) {
            mostrarAlerta("Error de conexión", "No se pudo conectar a la base de datos.", Alert.AlertType.ERROR);
            System.err.println("❌ Error de conexión: " + e.getMessage());
        }
    }

    @FXML
    protected void onLoginButtonClick() {
        errorText.setText("");

        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            errorText.setText("Por favor, complete todos los campos");
            return;
        }

        try {
            if (loginService.autenticarUsuario(username, password)) {
                mostrarAlerta("¡Éxito!", "Login realizado correctamente", Alert.AlertType.INFORMATION);
            } else {
                errorText.setText("Credenciales incorrectas");
                limpiarCampos();
            }
        } catch (Exception e) {
            errorText.setText("Error de conexión con la base de datos");
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        txtPassword.clear();
        txtPassword.requestFocus();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void onFieldClick() {
        errorText.setText("");
    }
}
