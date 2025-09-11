package sv.uees.inventory_management.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import sv.uees.inventory_management.service.LoginService;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label errorText;

    private final LoginService loginService = new LoginService();

    @FXML
    protected void onLoginButtonClick() {
        // Limpiar mensaje de error anterior
        errorText.setText("");

        // Obtener valores de los campos
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        // Validar que no estén vacíos
        if (username.isEmpty() || password.isEmpty()) {
            errorText.setText("Por favor, complete todos los campos");
            return;
        }

        try {
            // Intentar autenticar
            if (loginService.autenticarUsuario(username, password)) {
                // Login exitoso
                mostrarAlerta("¡Éxito!", "Login realizado correctamente", AlertType.INFORMATION);
                // Aquí abrirías la ventana principal:
                // abrirVentanaPrincipal();
            } else {
                // Login fallido
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

    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método opcional para limpiar al hacer clic en los campos
    @FXML
    private void onFieldClick() {
        errorText.setText("");
    }
}