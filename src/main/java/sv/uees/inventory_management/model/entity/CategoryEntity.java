package sv.uees.inventory_management.model.entity;

public class CategoryEntity {

    private int id;
    private String name;
    private String description;

    // Constructor vacío (necesario para el DAO)
    public CategoryEntity() {}

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}