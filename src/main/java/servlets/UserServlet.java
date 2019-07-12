package servlets;

import model.Role;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.impl.CoursesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(UserServlet.class);
    private CoursesServiceImpl coursesService = new CoursesServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getServletPath();
        logger.info("Enter method doPost, ACTION: {}", action);

        switch (action) {
            case "/registration":
                insertUser(request, response);
                break;
            case "/login":
                checkUserByLogAndPswd(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();
        logger.info("Enter method doGet, ACTION: {}", action);

        switch (action) {
            case "/":
                request.getRequestDispatcher("startPage.jsp").forward(request, response);
                break;
            case "/registration":
                request.getRequestDispatcher("WEB-INF/view/registration.jsp").forward(request, response);
                break;
            case "/login":
                request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("error");
        }
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        User user = new User(login, password, email, Role.STUDENT);
        logger.debug("Enter method insertUser: login:{}  email:{} ", login, email);

        try {
            if (coursesService.createUser(user)) {
                response.sendRedirect("login");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    private void checkUserByLogAndPswd(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        logger.debug("Method checkUserByLogAndPswd: login:{} ", login);

        try {
            if (coursesService.findUserByLogAndPswd(login, password))
                response.sendRedirect("coursesList");
            else response.sendRedirect("login");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
