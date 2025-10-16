package sv.uees.inventory_management.model.entity;

import java.sql.Timestamp;

public class LoginEntity {

    private int idUsuario;
    private String nombre;
    private String usuario;
    private String contrasena;
    private String rol;
    private Timestamp fechaRegistro;

    // Constructor vacío
    public LoginEntity() {}

    // Constructor para login rápido
    public LoginEntity(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Constructor completo
    public LoginEntity(int idUsuario, String nombre, String usuario, String rol, Timestamp fechaRegistro) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters & Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", usuario='" + usuario + '\'' +
                ", rol='" + rol + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}