package dao;

import model.Courses;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface CoursesDAO {
    List<Courses> listAllCourses() throws SQLException;
    boolean insertNewUser(User user) throws SQLException;
    boolean findUserByLogAndPswd(String login, String password) throws SQLException;
}
