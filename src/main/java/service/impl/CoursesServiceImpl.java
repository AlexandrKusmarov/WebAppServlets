package service.impl;

import dao.impl.CoursesDAOimpl;
import model.Courses;
import service.CoursesService;

import java.sql.SQLException;
import java.util.List;

public class CoursesServiceImpl implements CoursesService {
    @Override
    public List<Courses> getAllCourses() throws SQLException {
        return new CoursesDAOimpl().listAllCourses();
    }
}
