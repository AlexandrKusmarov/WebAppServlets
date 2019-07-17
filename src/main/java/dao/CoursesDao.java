package dao;

import model.Courses;

import java.sql.SQLException;
import java.util.List;

public interface CoursesDao {
    List<Courses> listAllCourses() throws SQLException;
}
