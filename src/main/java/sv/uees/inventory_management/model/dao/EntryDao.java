package sv.uees.inventory_management.model.dao;

import sv.uees.inventory_management.model.entity.EntryEntity;
import sv.uees.inventory_management.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EntryDao {

    private static final String SELECT_ALL_ENTRIES =
            "SELECT e.id_entrada, e.id_producto, e.id_sucursal, e.id_proveedor, e.cantidad, e.costo, e.fecha, " + // ⬅️ CORREGIDO: Usar 'e.fecha'
                    "p.nombre AS producto_nombre, s.nombre AS sucursal_nombre, pr.nombre AS proveedor_nombre " +
                    "FROM entradas e " +
                    "JOIN productos p ON e.id_producto = p.id_producto " +
                    "JOIN sucursales s ON e.id_sucursal = s.id_sucursal " +
                    "JOIN proveedores pr ON e.id_proveedor = pr.id_proveedor " +
                    "ORDER BY e.fecha DESC";

    public List<EntryEntity> findAll() {
        List<EntryEntity> entries = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_ENTRIES);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                entries.add(mapResultSetToEntry(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error en EntryDao al obtener entradas: " + e.getMessage());
            e.printStackTrace();
        }
        return entries;
    }

    private EntryEntity mapResultSetToEntry(ResultSet rs) throws SQLException {
        EntryEntity entry = new EntryEntity();

        entry.setId(rs.getInt("id_entrada"));
        entry.setProductId(rs.getInt("id_producto"));
        entry.setBranchId(rs.getInt("id_sucursal"));
        entry.setSupplierId(rs.getInt("id_proveedor"));
        entry.setQuantity(rs.getInt("cantidad"));
        entry.setCost(rs.getBigDecimal("costo"));

        Timestamp ts = rs.getTimestamp("fecha"); // ⬅️ CORREGIDO: Obtener por el nombre 'fecha'
        if (ts != null) {
            entry.setEntryDate(ts.toLocalDateTime());

        // Mapeo de JOINs
        entry.setProductName(rs.getString("producto_nombre"));
        entry.setBranchName(rs.getString("sucursal_nombre"));
        entry.setSupplierName(rs.getString("proveedor_nombre"));
        }
        return entry;
    }
}