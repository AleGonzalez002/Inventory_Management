package sv.uees.inventory_management.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import sv.uees.inventory_management.model.entity.UserEntity;
import sv.uees.inventory_management.service.UserService;

import java.io.IOException;
import java.util.Optional;

/**
 * Controlador que gestiona la interacción de la vista de registro (RegisterView.fxml).
 */
public class RegisterController {

    // Componentes FXML mapeados del RegisterView.fxml
    @FXML private TextField txtName;
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirmPassword;
    @FXML private Label errorText;

    // Servicios (Instancia directa, sin DI)
    private final UserService userService = new UserService();

    private static final String DEFAULT_ROLE = "empleado";
    private static final double SCENE_DELAY_SECONDS = 2.0; // 2 segundos de retraso para ver el mensaje

    // --- Punto de entrada para el Registro ---
    @FXML
    protected void onRegisterButtonClick() {
        clearError();

        String name = txtName.getText().trim();
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        // 1. Validaciones de la UI
        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showError("Debe completar todos los campos obligatorios.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError("Las contraseñas no coinciden.");
            return;
        }

        // 2. Preparar la Entidad
        UserEntity newUser = new UserEntity();
        newUser.setName(name);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole(DEFAULT_ROLE);

        // 3. Llamada al Servicio
        Optional<UserEntity> registeredUser = userService.registerUser(newUser);

        if (registeredUser.isPresent()) {
            // Éxito: Registro completado
            clearFields();
            showSuccessMessageAndRedirect();
        } else {
            // Fallo: Usuario duplicado o error de servicio
            showError("El registro falló. El usuario podría ya existir o hubo un error de sistema.");
        }
    }

    // --- Lógica de Retraso y Mensaje de Éxito ---
    /**
     * Muestra el mensaje de éxito y programa un retraso antes de cambiar a la escena de Login.
     */
    private void showSuccessMessageAndRedirect() {
        showError("¡Usuario registrado correctamente!...redirigiendo", false);

        // Define un Timeline (animación) para ejecutar una acción después de un retraso
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(SCENE_DELAY_SECONDS), // Duración del retraso
                        event -> {
                            try {
                                changeToLoginScene(); // Acción a ejecutar: cambiar de escena
                            } catch (IOException e) {
                                e.printStackTrace();
                                showError("Error crítico: No se pudo cargar la vista de Login.");
                            }
                        }
                )
        );
        timeline.play(); // Inicia el retraso
    }


    // --- Punto de entrada para regresar a Login ---
    @FXML
    protected void onLoginLinkClick() {
        try {
            changeToLoginScene();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            showError("Error crítico: No se pudo cargar la vista de Login.");
        }
    }

    // --- Lógica de Cambio de Escena ---
    private void changeToLoginScene() throws IOException {
        // Aseguramos que el Stage aún existe antes de intentar manipularlo
        Stage currentStage = (Stage) errorText.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/view/LoginView.fxml")
        );
        Scene loginScene = new Scene(fxmlLoader.load());

        currentStage.setScene(loginScene);
        currentStage.setTitle("");
        currentStage.show();
    }

    // --- Métodos Auxiliares ---

    private void clearFields() {
        txtName.clear();
        txtUsername.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
        txtName.requestFocus();
    }

    /**
     * Muestra un mensaje de error o éxito.
     */
    private void showError(String message, boolean isError) {
        errorText.setText(message);
        if (isError) {
            errorText.getStyleClass().setAll("text-danger");
        } else {
            errorText.getStyleClass().setAll("text-success"); // Necesita 'text-success' en tu CSS
        }
    }

    private void showError(String message) {
        showError(message, true);
    }

    private void clearError() {
        errorText.setText("");
    }

    @FXML
    private void onFieldClick() {
        clearError();
    }
}