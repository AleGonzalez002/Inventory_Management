package sv.uees.inventory_management.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {
    @FXML
    private Label errorText;

    @FXML
    protected void onLoginButtonClick() {
        errorText.setText("Credenciales Incorrectas");
    }
}