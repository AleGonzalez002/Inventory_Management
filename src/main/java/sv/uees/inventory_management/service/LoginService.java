package sv.uees.inventory_management.service;

import sv.uees.inventory_management.model.dao.LoginDao;
import sv.uees.inventory_management.model.entity.LoginEntity;

import java.sql.SQLException;

public class LoginService {

    private final LoginDao loginDao = new LoginDao();


    //Autentica un usuario con username y password.
    public boolean authenticateUser(String username, String password) throws SQLException {
        if (isNullOrEmpty(username) || isNullOrEmpty(password)) {
            return false;
        }

        LoginEntity user = loginDao.validateUser(username, password);
        return user != null;
    }


     //Valida si un string es nulo o vacío
    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}