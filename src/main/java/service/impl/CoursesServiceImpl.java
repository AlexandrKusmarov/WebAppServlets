package service.impl;

import dao.impl.CoursesDaoImpl;
import model.Courses;
import service.CoursesService;

import java.sql.SQLException;
import java.util.List;

public class CoursesServiceImpl implements CoursesService {
    private CoursesDaoImpl coursesDaoImpl = new CoursesDaoImpl();

    public List<Courses> getAllCourses() {
        return coursesDaoImpl.listAllCourses();
    }

    @Override
    public Courses getCourseById(Long idCourse) throws SQLException {
        return coursesDaoImpl.getCourseById(idCourse);
    }
}
