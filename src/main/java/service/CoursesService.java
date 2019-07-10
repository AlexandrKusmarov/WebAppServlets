package service;

import model.Courses;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface CoursesService {

    List<Courses> getAllCourses() throws SQLException;
    boolean createUser(User user) throws SQLException;
    boolean findUser(String login, String password) throws SQLException;
}
