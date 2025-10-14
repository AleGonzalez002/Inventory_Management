package sv.uees.inventory_management.model.entity;

public class LoginEntity {
    private int id;
    private String usuario;
    private String contrasena;
    private boolean activo = true;

    // Constructores
    public LoginEntity() {}

    public LoginEntity(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}