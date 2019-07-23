package servlets;

import model.Courses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.impl.CoursesServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CoursesServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(CoursesServlet.class);
    private CoursesServiceImpl coursesService = new CoursesServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getServletPath();
        logger.debug("Enter method doGet(). Action:{}", action);

        try{
            switch (action) {
                case "/coursesList" :
                    listCourses(req,resp);
                    break;
                case "/addCourses" :
                    req.getRequestDispatcher("WEB-INF/view/addCourses.jsp").forward(req,resp);
                    break;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        logger.debug("Enter method doPost(). Action:{}", action);

            switch (action) {
                case "/addCourses" :
                    createCourse(req,resp);
                    break;
                case "/deleteCourses" :
                    deleteCourse(req,resp);
                    break;
            }
    }

    private void listCourses (HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

            List<Courses> coursesList = coursesService.getAllCourses();

            request.setAttribute("coursesList", coursesList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/coursesList.jsp");
            dispatcher.forward(request, response);
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
            if(coursesService.createCourse(theme, courseName, java.sql.Date.valueOf(courseStart), java.sql.Date.valueOf(courseEnd), price)){
                resp.sendRedirect("coursesList");
            }
            else resp.sendRedirect("addCourses");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
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
        }
    }
}
