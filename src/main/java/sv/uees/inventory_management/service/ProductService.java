package sv.uees.inventory_management.model.service;

import sv.uees.inventory_management.model.dao.ProductDao;
import sv.uees.inventory_management.model.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

/**
 * Capa de servicio para la lógica de negocio relacionada con los Productos.
 * Actúa como intermediario entre el Controller y el DAO.
 */
public class ProductService {

    private final ProductDao productDao;

    public ProductService() {
        // El servicio inicializa su DAO.
        this.productDao = new ProductDao();
    }

    /**
     * Obtiene la lista completa de productos del DAO.
     * Aquí se podría añadir lógica como filtrar por permisos del usuario
     * o realizar algún cálculo antes de devolver los datos.
     * * @return Una lista de ProductEntity.
     */
    public List<ProductEntity> getAllProducts() {
        return productDao.findAll();
    }

    // --- Métodos CRUD básicos de ejemplo (DEBES IMPLEMENTARLOS en el DAO primero) ---

    /**
     * Guarda un nuevo producto. Aquí se añaden las validaciones.
     * @param product El producto a guardar.
     * @return El producto guardado con su ID.
     */
    /*
    public ProductEntity saveProduct(ProductEntity product) {
        // EJEMPLO DE LÓGICA DE NEGOCIO: Validar que el precio no sea negativo
        if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
        }
        return productDao.create(product);
    }
    */

    /**
     * Busca un producto por ID.
     * @param id El ID del producto.
     * @return Un Optional que contiene el producto si existe.
     */
    /*
    public Optional<ProductEntity> getProductById(int id) {
        return productDao.findById(id);
    }
    */
}