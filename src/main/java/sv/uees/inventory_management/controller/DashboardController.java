package sv.uees.inventory_management.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label statusLabel;

    /**
     * Se llama automáticamente después de que el FXML ha sido cargado.
     */
    @FXML
    public void initialize() {
        System.out.println("DashboardController inicializado correctamente.");
        statusLabel.setText("¡Dashboard cargado con éxito! ✅");
    }
}