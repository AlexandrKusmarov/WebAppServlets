package service;

import model.Courses;

import java.sql.SQLException;
import java.util.List;

public interface CoursesService {

    List<Courses> getAllCourses() throws SQLException;
}
