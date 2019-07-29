package service.impl;

import dao.impl.CoursesDaoImpl;
import model.Courses;
import service.CoursesService;

import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public void updateCourse(Long id, String theme, String courseName, Date courseStart, Date courseEnd, Integer price) throws SQLException {
        coursesDaoImpl.updateCourse(id, theme, courseName, courseStart, courseEnd, price);
    }

    @Override
    public void assignCoursesToTeacher(Long idTeacher, String[] checkBox) throws SQLException {
        coursesDaoImpl.assignCoursesToTeacher(idTeacher ,checkBox);
    }

    @Override
    public ArrayList<Long> getCoursesIdListByUserIdFromTableCts(Long id) throws SQLException {
        return coursesDaoImpl.getCoursesListByUserIdFromTableCts(id);
    }

    @Override
    public void looseCoursesFromTeacher(Long idTeacher, ArrayList<Long> idForDelete) throws SQLException {
        coursesDaoImpl.looseCoursesFromTeacher(idTeacher ,idForDelete);
    }
}
