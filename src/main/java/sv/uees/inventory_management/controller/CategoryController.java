package sv.uees.inventory_management.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sv.uees.inventory_management.model.entity.CategoryEntity;
import sv.uees.inventory_management.model.service.CategoryService;

import java.util.List;

public class CategoryController {

    // --- Inyecciones de FXML ---
    @FXML private TableView<CategoryEntity> categoriesTable;
    @FXML private TableColumn<CategoryEntity, Integer> colID;
    @FXML private TableColumn<CategoryEntity, String> colNombre; // Mapea a 'name'
    @FXML private TableColumn<CategoryEntity, String> colDescripcion; // Mapea a 'description'

    private CategoryService categoryService;
    private ObservableList<CategoryEntity> categoryList;

    @FXML
    public void initialize() {
        // 1. Inicializar Servicio y Lista
        this.categoryService = new CategoryService();
        this.categoryList = FXCollections.observableArrayList();

        // 2. Mapear columnas a las propiedades de CategoryEntity
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("description"));

        // 3. Cargar los datos
        loadCategories();
    }

    private void loadCategories() {
        try {
            categoryList.clear();
            List<CategoryEntity> categories = categoryService.getAllCategories();
            categoryList.addAll(categories);
            categoriesTable.setItems(categoryList);
        } catch (Exception e) {
            System.err.println("Error al cargar la lista de categorías: " + e.getMessage());
            // Manejar error en la interfaz
        }
    }

    // Aquí irían los métodos para añadir, editar y eliminar categorías
}