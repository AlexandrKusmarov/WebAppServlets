package dao.impl;

import dao.CoursesDAO;
import model.Courses;
import model.Role;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoursesDAOimpl implements CoursesDAO {

    private static Logger logger = LoggerFactory.getLogger(CoursesDAOimpl.class);

    @Override
    public List<Courses> listAllCourses() {

        logger.info("Enter method listAllCourses");

        List<Courses> coursesList = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int idCourses = resultSet.getInt("idCourses");
                String theme = resultSet.getString("theme");
                String nameOfCourses = resultSet.getString("nameOfCourses");
                Date startOfCourses = resultSet.getDate("startOfCourses");
                Date endOfCourses = resultSet.getDate("endOfCourses");
                int price = resultSet.getInt("price");

                Courses cours = new Courses(theme, nameOfCourses, startOfCourses, endOfCourses, price);
                coursesList.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return coursesList;
    }

    @Override
    public boolean insertNewUser(User user) {

        logger.info("Enter method insertNewUser");
        String sql = "INSERT INTO usr (login, password, email, userRole) VALUES (?, ?, ?, ?)";
        boolean rowInserted = false;

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, Role.STUDENT.toString());

            rowInserted = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return rowInserted;
    }

    public boolean findUserByLogAndPswd(String login, String password) {

        logger.info("Enter method findUserByLogAndPswd");
        String sql = "SELECT * FROM usr where login = ? AND password = ?";

        boolean isFind = false;
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                isFind = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return isFind;
    }
}