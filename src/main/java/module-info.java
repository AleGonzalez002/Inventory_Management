module sv.uees.inventory_management {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens sv.uees.inventory_management to javafx.fxml;
    exports sv.uees.inventory_management.controller;
    opens sv.uees.inventory_management.controller to javafx.fxml;
    exports sv.uees.inventory_management.app;
    opens sv.uees.inventory_management.app to javafx.fxml;
}