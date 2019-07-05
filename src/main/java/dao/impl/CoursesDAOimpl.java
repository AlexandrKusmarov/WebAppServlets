package dao.impl;

import dao.CoursesDAO;
import model.Courses;
import util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoursesDAOimpl implements CoursesDAO {

    @Override
    public List<Courses> listAllCourses() throws SQLException {

        List<Courses> coursesList = new ArrayList<>();

        String sql = "SELECT * FROM courses";

        try(Connection connection = DBUtil.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()){
                int idCourses = resultSet.getInt("idCourses");
                String theme = resultSet.getString("theme");
                String nameOfCourses = resultSet.getString("nameOfCourses");
                Date startOfCourses = resultSet.getDate("startOfCourses");
                Date endOfCourses = resultSet.getDate("endOfCourses");
                int price = resultSet.getInt("price");

                Courses cours = new Courses(theme, nameOfCourses, startOfCourses, endOfCourses, price);
                coursesList.add(cours);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return coursesList;
    }
}
