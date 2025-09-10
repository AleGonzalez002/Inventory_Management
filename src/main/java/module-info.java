module sv.uees.inventory_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens sv.uees.inventory_management.app to javafx.fxml;
    opens sv.uees.inventory_management.controller to javafx.fxml;

    exports sv.uees.inventory_management.app;
    exports sv.uees.inventory_management.controller;
}