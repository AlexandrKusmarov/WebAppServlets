package servlets;

import model.Courses;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.impl.CoursesServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoursesServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(CoursesServlet.class);
    private CoursesServiceImpl coursesService = new CoursesServiceImpl();
    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getServletPath();
        logger.debug("Enter method doGet(). Action:{}", action);

        switch (action) {
            case "/coursesList":
                listCourses(req, resp);
                req.getRequestDispatcher("WEB-INF/view/coursesList.jsp").forward(req, resp);
                break;
            case "/addCourses":
                req.getRequestDispatcher("WEB-INF/view/addCourses.jsp").forward(req, resp);
                break;
            case "/updateCourses":
                bindParamsOfCurrentCourse(req, resp);
                req.getRequestDispatcher("WEB-INF/view/updateCourses.jsp").forward(req, resp);
                break;
            case "/assignCourses":
                listCourses(req, resp);
                getTeacherName(req, resp);
                getAssignedCourses(req, resp);
                req.getRequestDispatcher("WEB-INF/view/assignCourses.jsp").forward(req, resp);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getServletPath();
        logger.debug("Enter method doPost(). Action:{}", action);

        switch (action) {
            case "/addCourses":
                createCourse(req, resp);
                break;
            case "/deleteCourses":
                deleteCourse(req, resp);
                break;
            case "/updateCourses":
                updateCourse(req, resp);
                break;
            case "/assignCourses":
                looseOrAssignCourses(req, resp);
                break;

        }
    }


    private void listCourses(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Enter method listCourses()");

        List<Courses> coursesList = coursesService.getAllCourses();

        request.setAttribute("coursesList", coursesList);
    }

    private void createCourse(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("Enter method createCourse()");

        String theme = req.getParameter("theme");
        String courseName = req.getParameter("courseName");
        String courseStart = req.getParameter("courseStart");
        String courseEnd = req.getParameter("courseEnd");
        Integer price = Integer.parseInt(req.getParameter("coursePrice"));

        logger.debug("Enter method insertCourse. Params: theme - {}," +
                        " courseName - {}, startCourse - {}, endCourse - {}, price - {}",
                theme, courseName, courseStart, courseEnd, price);
        try {
            if (coursesService.createCourse(theme, courseName, java.sql.Date.valueOf(courseStart), java.sql.Date.valueOf(courseEnd), price)) {
                resp.sendRedirect("coursesList");
            } else resp.sendRedirect("addCourses");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    private void deleteCourse(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("Enter method deleteCourse()");
        Long id = Long.parseLong(req.getParameter("idCourses"));
        try {
            coursesService.deleteCourse(id);
            resp.sendRedirect("coursesList");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    private void updateCourse(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("Enter method updateCourse()");
        System.out.println(req.getParameter("id"));
        Long id = Long.parseLong(req.getParameter("id"));
        System.out.println(req.getParameter("id"));
        try {
            String theme = req.getParameter("theme");
            String courseName = req.getParameter("courseName");
            String courseStart = req.getParameter("courseStart");
            String courseEnd = req.getParameter("courseEnd");
            Integer price = Integer.parseInt(req.getParameter("price"));

            coursesService.updateCourse(id, theme, courseName,
                    java.sql.Date.valueOf(courseStart), java.sql.Date.valueOf(courseEnd), price);
            resp.sendRedirect("coursesList");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    private void bindParamsOfCurrentCourse(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("Enter method bindParamsOfCurrentCourse()");
        Long id = Long.parseLong(req.getParameter("id"));

        try {
            Courses course = coursesService.getCourseById(id);
            req.setAttribute("id", id);
            req.setAttribute("theme", course.getTheme());
            req.setAttribute("courseName", course.getNameOfCourses());
            req.setAttribute("courseStart", course.getStartOfCourses());
            req.setAttribute("courseEnd", course.getEndOfCourses());
            req.setAttribute("price", course.getPrice());

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    private void getTeacherName(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("idUser"));
        logger.debug("Enter method getTeacherName() Param: id={}", id);
        req.setAttribute("idUser", id);

        try {
            User user = userService.getUserById(id);
            req.setAttribute("login", user.getLogin());
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    private void assignCourses(HttpServletRequest req, HttpServletResponse resp) {

        Long id = Long.parseLong(req.getParameter("idUser"));
        String[] checkBox = req.getParameterValues("assignCourses");
        logger.debug("Enter method assignCourses() Params: idTeacher={}, checkbox.length={}", id, checkBox.length);
        try {
            if (checkBox.length > 0) {
                coursesService.assignCoursesToTeacher(id, checkBox);
            }
            resp.sendRedirect("accounts");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    private void getAssignedCourses(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("idUser"));
        logger.debug("Enter method getAssignedCourses() Param: idUser={}", id);
        ArrayList<Long> coursesId;
        try {
            coursesId = coursesService.getCoursesIdListByUserIdFromTableCts(id);
            if (coursesId.size() > 0) {
                req.setAttribute("coursesId", coursesId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    private void looseOrAssignCourses(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("idUser"));
        logger.debug("Enter method looseOrAssign() Param: idUser={}", id);
        String[] checkBox = req.getParameterValues("assignCourses");
        try {
            ArrayList<Long> idList = coursesService.getCoursesIdListByUserIdFromTableCts(id);
            if (idList.size() > checkBox.length) {
                for (String aCheckBox : checkBox) {
                    Long idDel = Long.parseLong(aCheckBox);
                    idList.remove(idDel);
                }
                if (idList.size() > 0) {
                    coursesService.looseCoursesFromTeacher(id, idList);
                    resp.sendRedirect("accounts");
                }
            } else assignCourses(req, resp);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }
}
