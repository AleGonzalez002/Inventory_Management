package sv.uees.inventory_management.service;

import sv.uees.inventory_management.model.dao.LoginDao;
import sv.uees.inventory_management.model.entity.LoginEntity;

public class LoginService {
    private final LoginDao loginDao = new LoginDao();

    public boolean autenticarUsuario(String nombre, String contraseña) {
        if (nombre == null || nombre.trim().isEmpty()) return false;
        if (contraseña == null || contraseña.trim().isEmpty()) return false;

        LoginEntity usuario = loginDao.validarUsuario(nombre, contraseña);
        return usuario != null;
    }
}