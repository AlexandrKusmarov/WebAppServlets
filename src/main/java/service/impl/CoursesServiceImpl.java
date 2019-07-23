package service.impl;

import dao.impl.CoursesDaoImpl;
import model.Courses;
import service.CoursesService;

import java.sql.SQLException;
import java.util.Date;
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

    @Override
    public boolean createCourse(String theme, String courseName, Date courseStart, Date courseEnd, Integer price) throws SQLException {
        return coursesDaoImpl.insertCourse(theme, courseName, courseStart, courseEnd, price);
    }

    @Override
    public void deleteCourse(Long id) throws SQLException {
        coursesDaoImpl.removeCourseById(id);
    }
}
