package service.impl;

import dao.impl.CoursesDAOimpl;
import model.Courses;
import model.User;
import service.CoursesService;

import java.sql.SQLException;
import java.util.List;

public class CoursesServiceImpl implements CoursesService {
    @Override
    public List<Courses> getAllCourses() throws SQLException {
        return new CoursesDAOimpl().listAllCourses();
    }

    @Override
    public boolean createUser(User user) throws SQLException {
        return new CoursesDAOimpl().insertNewUser(user);
    }

    @Override
    public boolean findUserByLogAndPswd(String login, String password) {
        return new CoursesDAOimpl().findUserByLogAndPswd(login,password);
    }
}
