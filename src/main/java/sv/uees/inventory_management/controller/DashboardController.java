package sv.uees.inventory_management.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sv.uees.inventory_management.model.entity.UserEntity;

import java.io.IOException;

/**
 * Controlador principal del sistema. Gestiona la navegación lateral,
 * la carga dinámica de vistas y el cierre de sesión.
 */
public class DashboardController {

    // Controles FXML de la barra lateral
    @FXML private Label lblUsername;
    @FXML private Label lblRole;
    @FXML private Label statusLabel;
    @FXML private StackPane contentArea; // Área central para cargar las vistas

    // Botones de navegación (para manejar su estado, si es necesario)
    @FXML private Button btnProducts;
    @FXML private Button btnCategories;
    @FXML private Button btnUsers;

    // Objeto para mantener el estado del usuario autenticado (MUY IMPORTANTE)
    private UserEntity authenticatedUser;

    /**
     * Método de inicialización llamado automáticamente por JavaFX.
     * Aquí se puede cargar contenido inicial o realizar configuraciones.
     */
    @FXML
    public void initialize() {
        // Por ahora, solo puedes cargar el Dashboard, no el DashboardController,
        // desde el LoginController. Por eso, el usuario se setea después.
        // Aquí iría la carga de la vista principal por defecto (Ej: DashboardMetrics.fxml)
        System.out.println("DashboardController inicializado. Esperando datos de usuario...");
    }

    /**
     * Método para inyectar el usuario autenticado desde el LoginController.
     * Este método debe ser llamado inmediatamente después de cargar el DashboardView.fxml.
     * @param user El UserEntity autenticado.
     */
    public void setAuthenticatedUser(UserEntity user) {
        this.authenticatedUser = user;
        if (user != null) {
            // Actualizar la interfaz con la información del usuario
            lblUsername.setText(user.getUsername());
            lblRole.setText(user.getRole());

            // Lógica de permisos (ocultar botones si el rol es 'empleado')
            if ("empleado".equalsIgnoreCase(user.getRole())) {
                btnUsers.setDisable(true); // Ejemplo de restricción
            }
        }
    }


    // --- Métodos de Navegación ---

    @FXML
    protected void onProductsClick() {
        System.out.println("Navegando a la gestión de Productos.");
        // Lógica para cargar ProductsView.fxml en contentArea
    }

    @FXML
    protected void onCategoriesClick() {
        System.out.println("Navegando a la gestión de Categorías.");
        // Lógica para cargar CategoriesView.fxml en contentArea
    }

    @FXML
    protected void onUsersClick() {
        System.out.println("Navegando a la gestión de Usuarios.");
        // Lógica para cargar UsersView.fxml en contentArea
    }

    // --- Cierre de Sesión ---

    @FXML
    protected void onLogoutClick() {
        System.out.println("Cerrando sesión para el usuario: " + authenticatedUser.getUsername());

        // 1. Limpiar el estado de usuario (opcional, pero buena práctica)
        this.authenticatedUser = null;

        // 2. Regresar a la pantalla de Login
        try {
            changeToLoginScene();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error crítico al cargar la vista de Login después del cierre de sesión.");
        }
    }

    /**
     * Carga la escena de la vista de inicio de sesión (LoginView.fxml).
     */
    private void changeToLoginScene() throws IOException {
        // Usa un control FXML para obtener la ventana actual (Stage)
        Stage currentStage = (Stage) lblUsername.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/sv/uees/inventory_management/view/LoginView.fxml")
        );
        Scene loginScene = new Scene(fxmlLoader.load());

        currentStage.setScene(loginScene);
        currentStage.setTitle("Sistema de Inventario - Inicio de Sesión");
        currentStage.show();
    }
}