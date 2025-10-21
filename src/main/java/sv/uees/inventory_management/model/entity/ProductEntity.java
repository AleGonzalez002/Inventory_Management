package sv.uees.inventory_management.model.entity;

import java.math.BigDecimal;
// Importación necesaria para manejar fechas, aunque no la uses directamente en esta entidad, es buena práctica.
import java.time.LocalDateTime;

public class ProductEntity {

    // 1. Propiedades de la tabla PRODUCTOS
    private int id;
    private String name;
    private String description;
    private BigDecimal price; // Usar BigDecimal para el dinero

    // 2. Propiedades de Foreign Keys (IDs) - Útiles para guardar/editar
    private int categoryId;
    private int supplierId;

    // 3. Propiedades para el Dashboard/Tabla (campos que vienen del JOIN)
    private int stock; // Campo calculado (SUM) de la tabla 'inventario'
    private String categoryName; // Viene de la tabla 'categorias'
    private String supplierName; // Viene de la tabla 'proveedores'

    // Constructor vacío (necesario para la capa DAO)
    public ProductEntity() {
    }

    // --- Getters y Setters ---

    // ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Nombre
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Descripción
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Precio (BigDecimal)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // ID de Categoría
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    // ID de Proveedor
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    // STOCK (Calculado)
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Nombre de Categoría (del JOIN)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // Nombre de Proveedor (del JOIN)
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}