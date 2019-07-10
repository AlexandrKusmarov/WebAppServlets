package dao.impl;

import dao.CoursesDAO;
import model.Courses;
import model.Role;
import model.User;
import util.DBUtil;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoursesDAOimpl implements CoursesDAO {

    @Override
    public List<Courses> listAllCourses() throws SQLException {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coursesList;
    }

    @Override
    public boolean createUser(User user) throws SQLException {
        String sql = "INSERT INTO usr (login, password, email, userRole) VALUES (?, ?, ?, ?)";
        boolean rowInserted = false;

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, Role.STUDENT.toString());

            rowInserted = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    public boolean findUser(String login, String password) throws SQLException {

        String sql = "SELECT * FROM usr where login = ? AND password = ?";
        boolean isPresent = false;

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

             ps.setString(1,login);
             ps.setString(2, password);
             ResultSet resultSet = ps.executeQuery();

                if(resultSet.next()) {
                    String loginTab = resultSet.getString("login");
                    System.out.println("findUser: " + loginTab);
                    String passwordTab = resultSet.getString("password");
                    System.out.println("findUser: " + passwordTab);
                    if (loginTab.equals(login) && passwordTab.equals(password)) {
                        isPresent = true;
                    }
                }
        }
        return isPresent;
    }
}