package service;

import model.Courses;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface CoursesService {

    List<Courses> getAllCourses() throws SQLException;
    Courses getCourseById(Long idCourse) throws SQLException;

    boolean createCourse(String theme, String courseName, Date courseStart, Date courseEnd, Integer price) throws SQLException;

    void deleteCourse(Long id) throws SQLException;
}
