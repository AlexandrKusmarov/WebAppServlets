package dao.impl;

import dao.CoursesDao;
import model.Courses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ConnectionBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoursesDaoImpl implements CoursesDao {

    private static Logger logger = LoggerFactory.getLogger(CoursesDaoImpl.class);

    @Override
    public List<Courses> listAllCourses() {

        logger.info("Enter method listAllCourses");
        List<Courses> coursesList = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection connection = ConnectionBuilder.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Long idCourses = resultSet.getLong("idCourses");
                String theme = resultSet.getString("theme");
                String nameOfCourses = resultSet.getString("nameOfCourses");
                Date startOfCourses = resultSet.getDate("startOfCourses");
                Date endOfCourses = resultSet.getDate("endOfCourses");
                int price = resultSet.getInt("price");

                Courses cours = new Courses(idCourses ,theme, nameOfCourses, startOfCourses, endOfCourses, price);
                coursesList.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return coursesList;
    }

    @Override
    public Courses getCourseById(Long idCourse) throws SQLException {
        logger.debug("Enter method getCourseById(). idCourse={}",idCourse);
        String sql = "SELECT * FROM courses where  idCourses= ?";
        Courses courses = null;

        try (Connection connection = ConnectionBuilder.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCourse.intValue());
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                Long idCourses = resultSet.getLong("idCourses");
                String theme = resultSet.getString("theme");
                String courseName = resultSet.getString("nameOfCourses");
                Date courseStart = resultSet.getDate("startOfCourses");
                Date courseEnd = resultSet.getDate("endOfCourses");
                Integer price = resultSet.getInt("price");

                courses = new Courses(idCourses ,theme, courseName, courseStart, courseEnd, price);
                logger.info("Course was found");
            }
            else logger.info("Course was NOT found");
        }
        return courses;
    }

    @Override
    public boolean insertCourse(String theme, String courseName, Date courseStart, Date courseEnd, Integer price) {
        logger.debug("Enter method insertCourse. Params: theme - {}," +
                " courseName - {}, startCourse - {}, endCourse - {}, price - {}",
                theme, courseName, courseStart, courseEnd, price);
        boolean rowInserted = false;
        String sql = "INSERT INTO courses (theme, nameOfCourses, startOfCourses, endOfCourses, price) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, theme);
            ps.setString(2, courseName);
            ps.setDate(3, (java.sql.Date) courseStart);
            ps.setDate(4, (java.sql.Date) courseEnd);
            ps.setInt(5, price);

            rowInserted = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        if(rowInserted) {
            logger.info("Course <{}> was added", courseName);
        }
        else logger.error("Course <{}> was NOT added", courseName);
        return rowInserted;
    }

    @Override
    public void removeCourseById(Long id) throws SQLException {
        logger.debug("Enter method removeCourseById(). idCourse={}", id);
        String sql = "DELETE FROM courses where idCourses=?";

        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            if(ps.executeUpdate() > 0){
                logger.debug("Course with id <{}> was deleted", id);
            }
            else logger.error("Course with id <{}> was NOT deleted", id);
        }
    }
}
