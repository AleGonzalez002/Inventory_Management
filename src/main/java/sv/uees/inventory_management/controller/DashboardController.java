package sv.uees.inventory_management.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sv.uees.inventory_management.model.entity.UserEntity;

import java.io.IOException;

/**
 * Controlador principal del sistema (Dashboard).
 * Gestiona la navegación lateral, el estado del usuario y el cierre de sesión.
 */
public class DashboardController {

    // --- Componentes FXML ---
    @FXML private Label lblUsername;
    @FXML private Label lblRole;
    @FXML private Label statusLabel;
    @FXML private StackPane contentArea; // Área central para cargar las vistas dinámicas
    @FXML private Button btnSuppliers;

    // Botones de navegación (para manejar su estado/permisos)
    @FXML private Button btnProducts;
    @FXML private Button btnCategories;
    @FXML private Button btnUsers;

    // --- Estado de la Aplicación ---
    private UserEntity authenticatedUser;

    @FXML
    public void initialize() {
        // Lógica de inicialización (ej. cargar la vista de bienvenida por defecto)
        // Por ahora, solo imprime en consola.
        System.out.println("DashboardController inicializado.");
    }

    /**
     * Inyecta el usuario autenticado desde el LoginController y actualiza la UI.
     * Este método debe llamarse después de cargar DashboardView.fxml.
     * @param user El UserEntity autenticado.
     */
    public void setAuthenticatedUser(UserEntity user) {
        this.authenticatedUser = user;
        if (user != null) {
            // Actualizar la interfaz con la información del usuario
            lblUsername.setText("👤 " + user.getUsername());
            lblRole.setText(user.getRole());

            // Lógica de permisos (ej. deshabilitar botón de Usuarios para 'empleados')
            if ("empleado".equalsIgnoreCase(user.getRole())) {
                btnUsers.setDisable(true);
            }
        }
    }


    // --- Métodos de Navegación ---

    @FXML
    protected void onProductsClick() {
        System.out.println("Navegando a la gestión de Productos.");
        // Carga la vista de Productos en el área central
        loadView("/sv/uees/inventory_management/view/ProductView.fxml");
    }

    @FXML
    protected void onCategoriesClick() {
        System.out.println("Navegando a la gestión de Categorías.");
        loadView("/sv/uees/inventory_management/view/CategoryView.fxml");
    }

    @FXML
    protected void onUsersClick() {
        System.out.println("Navegando a la gestión de Usuarios.");
        loadView("/sv/uees/inventory_management/sv.uees.inventory_management.view/UsersView.fxml");
    }

    @FXML
    protected void onDashboardClick() {
        System.out.println("Navegando a Dashboard Summary.");
        loadView("/sv/uees/inventory_management/sv.uees.inventory_management.view/DashboardSummaryView.fxml");
    }

    @FXML
    protected void onInboundClick() {
        System.out.println("Navegando a Registro de Entradas (Ingreso de Stock).");
        loadView("/sv/uees/inventory_management/view/EntryView.fxml");
    }

    @FXML
    protected void onOutboundClick() {
        System.out.println("Navegando a Registro de Salidas (Egreso de Stock).");
        loadView("/sv/uees/inventory_management/sv.uees.inventory_management.view/OutboundView.fxml");
    }

    @FXML
    protected void onReportsClick() {
        System.out.println("Navegando a la generación de Reportes.");
        loadView("/sv/uees/inventory_management/sv.uees.inventory_management.view/ReportsView.fxml");
    }

    // Añade el método de acción
    @FXML
    protected void onSuppliersClick() {
        System.out.println("Navegando a la gestión de Proveedores.");
        loadView("/sv/uees/inventory_management/sv.uees.inventory_management.view/SuppliersView.fxml");
    }

    /**
     * Carga un archivo FXML dado y lo coloca en el StackPane central (contentArea).
     * @param fxmlPath La ruta del archivo FXML (ej: "/sv/uees/inventory_management/sv.uees.inventory_management.view/ProductsView.fxml")
     */
    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node view = loader.load();

            // 1. Limpiar el área central
            contentArea.getChildren().clear();

            // 2. Agregar la nueva vista
            contentArea.getChildren().add(view);

            System.out.println("Vista cargada con éxito: " + fxmlPath);

        } catch (IOException e) {
            System.err.println("Error al cargar la vista FXML: " + fxmlPath);
            e.printStackTrace();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(new Label("Error: No se pudo cargar la vista. Revise la ruta del FXML."));
        }
    }


    // --- Cierre de Sesión ---

    @FXML
    protected void onLogoutClick() {
        if (authenticatedUser != null) {
            System.out.println("Cerrando sesión para: " + authenticatedUser.getUsername());
        }

        // 1. Limpiar el estado (opcional)
        this.authenticatedUser = null;

        // 2. Regresar a la pantalla de Login
        try {
            changeToLoginScene();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error crítico al cargar la vista de Login.");
        }
    }

    /**
     * Carga la escena de la vista de inicio de sesión (LoginView.fxml).
     */
    private void changeToLoginScene() throws IOException {
        // Usamos un control FXML para obtener la ventana actual (Stage)
        Stage currentStage = (Stage) lblUsername.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/sv/uees/inventory_management/view/LoginView.fxml")
        );
        Scene loginScene = new Scene(fxmlLoader.load());

        currentStage.setScene(loginScene);
        currentStage.setTitle("");

        // Al regresar al login, centramos la ventana
        currentStage.centerOnScreen();
        currentStage.show();
    }
}