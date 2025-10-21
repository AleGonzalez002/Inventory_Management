package sv.uees.inventory_management.model.dao;

import sv.uees.inventory_management.model.entity.ProductEntity;
import sv.uees.inventory_management.utils.DatabaseConnection; // Usando tu clase de conexión

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal; // Importación necesaria

public class ProductDao {

    // CONSULTA CON JOINS: Trae el nombre de la categoría y proveedor.
    private static final String SELECT_ALL_PRODUCTS =
            "SELECT p.id_producto, p.nombre, p.descripcion, p.precio, " +
                    "c.nombre AS nombre_categoria, pr.nombre AS nombre_proveedor, " +
                    "COALESCE(SUM(i.stock), 0) AS stock_total " + // Usamos SUM(i.stock) para obtener el total de todas las sucursales
                    "FROM productos p " +
                    "LEFT JOIN categorias c ON p.id_categoria = c.id_categoria " +
                    "LEFT JOIN proveedores pr ON p.id_proveedor = pr.id_proveedor " +
                    "LEFT JOIN inventario i ON p.id_producto = i.id_producto " +
                    "GROUP BY p.id_producto, p.nombre, p.descripcion, p.precio, c.nombre, pr.nombre";
    // Agregamos todas las columnas sin agregación al GROUP BY

    public List<ProductEntity> findAll() {
        List<ProductEntity> products = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect(); // Usando tu método de conexión
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en ProductDao al obtener todos los productos: " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Mapea un ResultSet a un objeto ProductEntity usando nomenclatura en inglés.
     */
    private ProductEntity mapResultSetToProduct(ResultSet rs) throws SQLException {
        ProductEntity product = new ProductEntity();

        // Mapeo de columnas SQL (Español) a Setters Java (Inglés)
        product.setId(rs.getInt("id_producto"));
        product.setName(rs.getString("nombre")); // Mapea columna 'nombre' a setName()
        product.setDescription(rs.getString("descripcion"));
        product.setPrice(rs.getBigDecimal("precio")); // Mapea columna 'precio' a setPrice()

        // Mapeo de campos de JOIN y Stock
        product.setCategoryName(rs.getString("nombre_categoria")); // Mapea el alias 'nombre_categoria'
        product.setSupplierName(rs.getString("nombre_proveedor")); // Mapea el alias 'nombre_proveedor'
        product.setStock(rs.getInt("stock_total")); // Mapea el alias 'stock_total'

        return product;
    }

    // ... otros métodos CRUD (create, update, delete) ...
}