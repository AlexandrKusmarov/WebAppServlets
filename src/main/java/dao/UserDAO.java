package dao;

import model.Courses;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<Courses> listAllCourses() throws SQLException;
    boolean insertNewUser(User user) throws SQLException;
    boolean findUserByLoginAndPswd(String login, String password) throws SQLException;
    boolean findUserByLogin(String login) throws SQLException;
}
