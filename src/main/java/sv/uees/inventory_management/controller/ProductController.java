package sv.uees.inventory_management.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sv.uees.inventory_management.model.entity.ProductEntity;
import sv.uees.inventory_management.model.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class ProductController {

    // --- Inyecciones de FXML (TableView y Columns) ---
    @FXML private TableView<ProductEntity> productsTable;
    @FXML private TableColumn<ProductEntity, Integer> colID;
    @FXML private TableColumn<ProductEntity, String> colNombre;
    @FXML private TableColumn<ProductEntity, String> colCategoria;
    @FXML private TableColumn<ProductEntity, Integer> colStock;
    @FXML private TableColumn<ProductEntity, BigDecimal> colPrecio;
    @FXML private TableColumn<ProductEntity, String> colProveedor;
    @FXML private TableColumn<ProductEntity, Void> colAcciones; // Para futuros botones de Edit/Delete

    // --- Capa de Servicio ---
    private ProductService productService;
    private ObservableList<ProductEntity> productList;

    /**
     * Se ejecuta automáticamente después de cargar el FXML.
     */
    @FXML
    public void initialize() {
        // 1. Inicializar componentes
        this.productService = new ProductService();
        this.productList = FXCollections.observableArrayList();

        // 2. Configurar el mapeo de columnas
        setupTableColumns();

        // 3. Cargar los datos iniciales
        loadProducts();
    }

    /**
     * Mapea cada columna de la tabla a la propiedad (getter en inglés) de ProductEntity.
     * Ejemplo: colNombre se mapea a getName() de ProductEntity.
     */
    private void setupTableColumns() {
        // Propiedades básicas de la tabla PRODUCTOS
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("name")); // <-- Mapea a getName()
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("price")); // <-- Mapea a getPrice()

        // Propiedades obtenidas vía JOIN/Cálculo
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock")); // <-- Mapea a getStock()
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoryName")); // <-- Mapea a getCategoryName()
        colProveedor.setCellValueFactory(new PropertyValueFactory<>("supplierName")); // <-- Mapea a getSupplierName()

        // Formato: Configurar formato de moneda para la columna de precio
        // (Opcional, requiere un CellFactory customizado para BigDecimal)
    }

    /**
     * Llama al Service para obtener los datos de la DB y los carga en la TableView.
     */
    private void loadProducts() {
        try {
            productList.clear();

            // Obtener datos del servicio (el service llama al DAO)
            List<ProductEntity> products = productService.getAllProducts();

            // Cargar datos a la lista observable
            productList.addAll(products);

            // Asignar la lista a la tabla
            productsTable.setItems(productList);

        } catch (Exception e) {
            // En un proyecto real, se mostraría un Alert de JavaFX al usuario
            System.err.println("Error al cargar la lista de productos: " + e.getMessage());
        }
    }

    // --- Lógica de Botones (Pendiente de implementación) ---

    // @FXML
    // private void onAddProductClick() {
    //     // Lógica para abrir la ventana de añadir producto
    // }
}