package dao.impl;

import model.Courses;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CoursesDAOimplTest {

    @BeforeClass
    public static void startUp() throws Exception {
        DbInit.startUp();
    }

    @Test
    public void listAllCourses() {
        List<Courses> actual = Arrays.asList(
                new Courses("Beginner", "First steps in Java", new Date(2019,8,1) , new Date(2019,12,1), 20),
                new Courses("Beginner", "Second steps in Java", new Date(2019,1,20) , new Date(2020,7,20), 25),
                new Courses("Advanced", "Codding like a boss", new Date(2020,8,20) , new Date(2021,12,25), 40)
        );
        List<Courses> expected = new CoursesDAOimpl().listAllCourses();
        for (Courses c:actual) {
            System.out.println(c.getTheme() + c.getNameOfCourses() + c.getStartOfCourses() + c.getEndOfCourses() + c.getPrice());
        }

        assertThat(actual, is(expected));
    }

    @Test
    public void insertNewUser() {
    }

    @Test
    public void findUserByLogAndPswd() {
    }
}