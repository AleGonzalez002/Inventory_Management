package sv.uees.inventory_management.model.service;

import sv.uees.inventory_management.model.dao.EntryDao;
import sv.uees.inventory_management.model.dao.InventoryDao;
import sv.uees.inventory_management.model.entity.EntryEntity;
// ... otras importaciones (ProductDao, etc.) ...

import java.util.List;

/**
 * Servicio Central para la gestión de Stock, que incluye transacciones y lectura de historial.
 */
public class InventoryService {

    private final InventoryDao inventoryDao;
    private final EntryDao entryDao; // Necesario para obtener el historial de entradas
    // private final ExitDao exitDao; // Lo necesitarás para la transacción de salidas

    public InventoryService() {
        this.inventoryDao = new InventoryDao();
        this.entryDao = new EntryDao();
        // this.exitDao = new ExitDao();
    }

    /**
     * Obtiene todo el historial de movimientos de ENTRADA. (Lectura para la vista)
     * Llama directamente a EntryDao.findAll().
     */
    public List<EntryEntity> getAllEntryHistory() {
        return entryDao.findAll();
    }

    // Aquí irá el método para CREAR una entrada (transaccional)
    /*
    public void createEntryTransaction(EntryEntity entry) {
        // 1. Guardar el registro en la tabla 'entradas' (EntryDao.save())
        // 2. Actualizar el stock en la tabla 'inventario' (InventoryDao.updateStock())
    }
    */
}