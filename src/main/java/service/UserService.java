package service;

import model.Role;
import model.User;

import java.sql.SQLException;

public interface UserService {

    boolean createUser(User user) throws SQLException;
    boolean findUserByLoginAndPswd(String login, String password) throws SQLException;
    boolean findUserByLogin(String login) throws SQLException;
    Role getCurrentUserRole(String login) throws SQLException;
}
