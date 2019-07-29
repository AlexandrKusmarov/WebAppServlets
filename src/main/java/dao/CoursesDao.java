package dao;

import model.Courses;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface CoursesDao {
    List<Courses> listAllCourses() throws SQLException;
    Courses getCourseById(Long idCourse) throws SQLException;
    boolean insertCourse(String theme, String courseName, Date courseStart, Date courseEnd, Integer price) throws SQLException;
    void removeCourseById(Long id) throws SQLException;
    void updateCourse(Long id, String theme, String courseName, Date courseStart, Date courseEnd, Integer price) throws SQLException;
    void assignCoursesToTeacher(Long idTeacher, String[] checkBox) throws SQLException;
    ArrayList<Long> getCoursesListByUserIdFromTableCts(Long id) throws SQLException;
    void looseCoursesFromTeacher(Long idTeacher, ArrayList<Long> idForDelete)throws SQLException ;
}
