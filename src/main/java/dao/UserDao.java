package dao;

import model.Role;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    boolean insertNewUser(User user) throws SQLException;
    boolean findUserByLoginAndPswd(String login, String password) throws SQLException;
    boolean findUserByLogin(String login) throws SQLException;
    Role getCurrentUserRole(String login) throws SQLException;
    void editTeacher(User user);

    List<User> listAccounts() throws SQLException;
}
