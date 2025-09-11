package sv.uees.inventory_management.model.entity;

public class LoginEntity {
    private int id;
    private String username;
    private String password;
    private String nombre;
    private boolean activo = true;

    // Constructores
    public LoginEntity() {}

    public LoginEntity(String username, String password) {
        this.username = username;
        this.password = password;
        this.nombre = username;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) {
        this.username = username;
        this.nombre = username;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.username = nombre;
    }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}