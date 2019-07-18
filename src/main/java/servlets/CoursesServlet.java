package servlets;

import model.Courses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.impl.CoursesServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void listCourses (HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

//        if (request.getSession().getAttribute("userName") == null
//                || request.getSession().getAttribute("role") == null) {
//            response.sendRedirect("login");
//        } else {}
            List<Courses> coursesList = coursesService.getAllCourses();

            request.setAttribute("coursesList", coursesList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/coursesList.jsp");
            dispatcher.forward(request, response);

        }
}
