package service.impl;

import dao.impl.CoursesDAOimpl;
import model.Courses;
import model.User;
import service.CoursesService;

import java.sql.SQLException;
import java.util.List;

public class CoursesServiceImpl implements CoursesService {

    private CoursesDAOimpl coursesDAOimpl = new CoursesDAOimpl();

    @Override
    public List<Courses> getAllCourses() {
        return coursesDAOimpl.listAllCourses();
    }

    @Override
    public boolean createUser(User user) {
        return coursesDAOimpl.insertNewUser(user);
    }

    @Override
    public boolean findUserByLoginAndPswd(String login, String password) {
        return coursesDAOimpl.findUserByLoginAndPswd(login, password);
    }

    @Override
    public boolean findUserByLogin(String login) {
        return coursesDAOimpl.findUserByLogin(login);
    }
}
