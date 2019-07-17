package service.impl;

import dao.impl.UserDaoimpl;
import model.Role;
import model.User;
import service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private UserDaoimpl userDAOimpl = new UserDaoimpl();

    @Override
    public boolean createUser(User user) {
        return userDAOimpl.insertNewUser(user);
    }

    @Override
    public boolean findUserByLoginAndPswd(String login, String password) {
        return userDAOimpl.findUserByLoginAndPswd(login, password);
    }

    @Override
    public boolean findUserByLogin(String login) {
        return userDAOimpl.findUserByLogin(login);
    }

    @Override
    public Role getCurrentUserRole(String login) throws SQLException {
        return userDAOimpl.getCurrentUserRole(login);
    }
}
