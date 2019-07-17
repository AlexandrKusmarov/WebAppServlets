package dao.impl;

import model.Courses;
import model.Role;
import model.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class UserDaoimplTest {

    @BeforeClass
    public static void startUp() throws Exception {
        DbInit.startUp();
    }

    @Test
    public void listAllCourses() {
        List<Courses> coursesList = new CoursesDaoImpl().listAllCourses();
        Assert.assertEquals(coursesList.size(),coursesList.size());
    }

    @Test
    public void insertNewUser() {
        new UserDaoimpl().insertNewUser(new User("test","test","test@test", Role.STUDENT));
    }

    @Test
    public void findUserByLog() {
        new UserDaoimpl().findUserByLogin("test");
    }
}