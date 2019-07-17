package dao.impl;

import dao.CoursesDao;
import model.Courses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ConnectionBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoursesDaoImpl implements CoursesDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoimpl.class);

    @Override
    public List<Courses> listAllCourses() {

        logger.info("Enter method listAllCourses");

        List<Courses> coursesList = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection connection = ConnectionBuilder.getConnection();
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
}
