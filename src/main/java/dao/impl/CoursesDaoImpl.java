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

    @Override
    public void updateCourse(Long id, String theme, String courseName, Date courseStart, Date courseEnd, Integer price) throws SQLException {
        logger.debug("Enter method updateCourse().Params: " +
                        "idCourse={}, theme={}, courseName={}, courseStart={}, courseEnd={}, price={}",
                id, theme, courseName, courseStart, courseEnd, price);

        String sql = "UPDATE courses SET " +
                "theme=?, nameOfCourses=?, startOfCourses=?, endOfCourses=?, price=? " +
                "WHERE idCourses=?";
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, theme);
            ps.setString(2, courseName);
            ps.setDate(3, (java.sql.Date) courseStart);
            ps.setDate(4, (java.sql.Date) courseEnd);
            ps.setInt(5, price);
            ps.setLong(6, id);

            if(ps.executeUpdate() > 0){
                logger.debug("Course with id <{}> was updated", id);
            }
            else logger.error("Course with id <{}> was NOT updated", id);
        }
    }

    @Override
    public void assignCoursesToTeacher(Long idTeacher, String[] checkBox) throws SQLException {
        logger.debug("Enter method assignCoursesToTeacher().Params: idUser={}, checkbox.length={}", idTeacher, checkBox.length);

        for (String box : checkBox) {
            if (!isCourseAlreadyAssigned(idTeacher, Integer.parseInt(box))) {

                String sql = "INSERT INTO cts(teacherId,courseId) VALUES (?, ?)";
                try (Connection connection = ConnectionBuilder.getConnection();
                     PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setLong(1, idTeacher);
                    ps.setInt(2, Integer.parseInt(box));

                    if (ps.executeUpdate() > 0) {
                        logger.debug("Course with id <{}> was assigned to teacher with id={}", Integer.parseInt(box), idTeacher);
                    } else
                        logger.debug("Course with id <{}> was NOT assigned to teacher with id={}", Integer.parseInt(box), idTeacher);
                }
            }
        }
    }

    private boolean isCourseAlreadyAssigned(Long idTeacher, Integer idCourse) {
        logger.debug("Enter method isCourseAlreadyAssigned().Params: idTeacher={}, idCourse={}", idTeacher, idCourse);
        boolean isAssigned = false;

        String sql = "SELECT * FROM cts WHERE teacherId=? AND courseId=?";
        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, idTeacher);
            ps.setInt(2, idCourse);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                isAssigned = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }
        return isAssigned;
    }

    @Override
    public ArrayList<Long> getCoursesListByUserIdFromTableCts(Long id) {
        logger.debug("Enter method getCoursesIdListByUserIdFromTableCts().Param: idTeacher={}", id);
        String sql = "SELECT * FROM cts where teacherId=?";
        ArrayList<Long> courses = new ArrayList<>();

        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);

            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                    courses.add(resultSet.getLong("courseId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }
        logger.info("Return idList with length={}", courses.size());
        return courses;
    }

    @Override
    public void looseCoursesFromTeacher(Long idTeacher, ArrayList<Long> idForDelete) throws SQLException {
        logger.debug("Enter method looseCoursesFromTeacher().Param: idForDelete.size()={}", idForDelete.size());
        String sql = "DELETE FROM cts WHERE teacherId=? AND courseId=?";

        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            for (Long aLong : idForDelete) {
                ps.setLong(1, idTeacher);
                ps.setLong(2, aLong);
                ps.executeUpdate();
            }
        }
        logger.info("Courses was loosed from teacher");
    }
}
