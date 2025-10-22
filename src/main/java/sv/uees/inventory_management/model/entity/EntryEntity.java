package sv.uees.inventory_management.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EntryEntity {

    private int id;
    private int productId;
    private int branchId;
    private int supplierId;
    private int quantity;
    private BigDecimal cost; // Usar BigDecimal para el costo
    private LocalDateTime entryDate;

    // Propiedades del JOIN para la vista
    private String productName;
    private String branchName;
    private String supplierName;

    // Constructor vacío
    public EntryEntity() {}

    // Getters y Setters (Asegúrate de tenerlos todos)

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getBranchId() { return branchId; }
    public void setBranchId(int branchId) { this.branchId = branchId; }

    public int getSupplierId() { return supplierId; }
    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }

    public LocalDateTime getEntryDate() { return entryDate; }
    public void setEntryDate(LocalDateTime entryDate) { this.entryDate = entryDate; }

    // Getters y Setters de JOIN
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
}