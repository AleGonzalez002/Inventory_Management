package sv.uees.inventory_management.service;

import sv.uees.inventory_management.model.dao.LoginDao;
import sv.uees.inventory_management.model.entity.LoginEntity;

public class LoginService {
    private final LoginDao loginDao = new LoginDao();

    public boolean autenticarUsuario(String usuario, String contrasena) {
        if (usuario == null || usuario.trim().isEmpty()) return false;
        if (contrasena == null || contrasena.trim().isEmpty()) return false;

        LoginEntity Login = loginDao.validarUsuario(usuario, contrasena);
        return Login != null;
    }
}