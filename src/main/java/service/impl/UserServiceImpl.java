package service.impl;

import dao.impl.UserDaoimpl;
import model.Role;
import model.User;
import service.UserService;

import java.sql.SQLException;
import java.util.List;

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

    @Override
    public void editTecher(User user) throws SQLException {
        userDAOimpl.editTeacher(user);
    }

    @Override
    public List<User> listAccounts() throws SQLException {
        return userDAOimpl.listAccounts();
    }

    @Override
    public User getUserById(Long id) throws SQLException {
        return userDAOimpl.getUserById(id);
    }

    @Override
    public void editUser(Long id, String login, String password, String email, String userRole, boolean isActive) throws SQLException {
        userDAOimpl.updateUser(id, login, password, email, userRole, isActive);

    }

    @Override
    public boolean checkPermission(String userName) throws SQLException {
        return userDAOimpl.checkPermission(userName);
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        return userDAOimpl.getUserByLogin(login);
    }
}
