package sv.uees.inventory_management.service;

import sv.uees.inventory_management.model.dao.LoginDao;
import sv.uees.inventory_management.model.entity.LoginEntity;

import java.sql.SQLException;

public class LoginService {

    private final LoginDao loginDao = new LoginDao();

    public boolean authenticateUser(String username, String password) throws SQLException {
        if (username == null || username.trim().isEmpty()) return false;
        if (password == null || password.isEmpty()) return false;

        LoginEntity user = loginDao.validateUser(username, password);
        return user != null;
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
