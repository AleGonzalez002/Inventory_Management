package sv.uees.inventory_management.model.dao;

import sv.uees.inventory_management.model.entity.CategoryEntity;
import sv.uees.inventory_management.utils.DatabaseConnection; // Usando tu clase de conexión

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private static final String SELECT_ALL_CATEGORIES =
            "SELECT id_categoria, nombre, descripcion FROM categorias ORDER BY nombre";

    public List<CategoryEntity> findAll() {
        List<CategoryEntity> categories = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_CATEGORIES);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categories.add(mapResultSetToCategory(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en CategoryDao al obtener categorías: " + e.getMessage());
            e.printStackTrace();
        }
        return categories;
    }

    private CategoryEntity mapResultSetToCategory(ResultSet rs) throws SQLException {
        CategoryEntity category = new CategoryEntity();
        // Mapeo de columnas SQL (Español) a Setters Java (Inglés)
        category.setId(rs.getInt("id_categoria"));
        category.setName(rs.getString("nombre"));
        category.setDescription(rs.getString("descripcion"));
        return category;
    }
}