package dao.impl;

import dao.UserDao;
import model.Courses;
import model.Role;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ConnectionBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoimpl implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoimpl.class);

    @Override
    public boolean insertNewUser(User user) {

        logger.info("Enter method insertNewUser");
        boolean rowInserted = false;

            String sql = "INSERT INTO usr (login, password, email, userRole) VALUES (?, ?, ?, ?)";

            try (Connection connection = ConnectionBuilder.getConnection();
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

    public boolean findUserByLoginAndPswd(String login,String password) {

        logger.info("Enter method findUserByLoginAndPswd");
        String sql = "SELECT * FROM usr where login = ? AND password= ?";

        boolean isFind = false;
        try (Connection connection = ConnectionBuilder.getConnection();
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

    @Override
    public boolean findUserByLogin(String login) {

        boolean isFind = false;
        logger.info("Enter method findUserByLogin");
        String sql = "SELECT * FROM usr where login = ?";

        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, login);
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

    @Override
    public Role getCurrentUserRole(String login) throws SQLException {

        String role = "";
        logger.info("Enter method getCurrentUserRole");
        String sql = "SELECT userRole FROM usr where login = ?";

        try (Connection connection = ConnectionBuilder.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                role = resultSet.getString("userRole");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return Role.valueOf(role);
    }
}