package service;

import model.Role;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    boolean createUser(User user) throws SQLException;
    boolean findUserByLoginAndPswd(String login, String password) throws SQLException;
    boolean findUserByLogin(String login) throws SQLException;
    Role getCurrentUserRole(String login) throws SQLException;
    void editTecher(User user) throws SQLException;

    List<User> listAccounts() throws SQLException;
    User getUserById(Long id) throws SQLException;
    void editUser(Long id, String login, String password, String email, String userRole, boolean isActive) throws SQLException;

    boolean checkPermission(String userName)throws SQLException ;
}
