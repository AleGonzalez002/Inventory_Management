package sv.uees.inventory_management.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sv.uees.inventory_management.model.entity.EntryEntity;
import sv.uees.inventory_management.model.service.InventoryService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class EntryController {

    // Asegúrate de que estos fx:id coincidan con tu EntryView.fxml
    @FXML private TableView<EntryEntity> entriesTable;
    @FXML private TableColumn<EntryEntity, Integer> colID;
    @FXML private TableColumn<EntryEntity, String> colProducto;
    @FXML private TableColumn<EntryEntity, String> colSucursal;
    @FXML private TableColumn<EntryEntity, String> colProveedor;
    @FXML private TableColumn<EntryEntity, Integer> colCantidad;
    @FXML private TableColumn<EntryEntity, BigDecimal> colCosto;
    @FXML private TableColumn<EntryEntity, LocalDateTime> colFecha;

    // ⭐️ CORRECCIÓN 1: La variable debe llamarse 'inventoryService'
    private InventoryService inventoryService;
    private ObservableList<EntryEntity> entryList;

    @FXML
    public void initialize() {
        // Inicialización correcta
        this.inventoryService = new InventoryService();
        this.entryList = FXCollections.observableArrayList();

        setupTableColumns();
        loadEntries();
    }

    private void setupTableColumns() {
        // Mapear a los getters en inglés de EntryEntity
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProducto.setCellValueFactory(new PropertyValueFactory<>("productName")); // Del JOIN
        colSucursal.setCellValueFactory(new PropertyValueFactory<>("branchName"));   // Del JOIN
        colProveedor.setCellValueFactory(new PropertyValueFactory<>("supplierName")); // Del JOIN
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
    }

    private void loadEntries() {
        try {
            entryList.clear();
            // ⭐️ CORRECCIÓN 2: El método debe ser el que definimos en InventoryService
            List<EntryEntity> entries = inventoryService.getAllEntryHistory();
            entryList.addAll(entries);
            entriesTable.setItems(entryList);
        } catch (Exception e) {
            System.err.println("Error al cargar la lista de entradas: " + e.getMessage());
        }
    }
}