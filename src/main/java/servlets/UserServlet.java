package servlets;

import model.Role;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.impl.CoursesServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(UserServlet.class);
    private UserServiceImpl userService = new UserServiceImpl();
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
                checkUserByLoginAndPswd(request, response);
                break;
            case "/logout":
                logout(request,response);
                break;
            case "/addTeacher":
                addNewTeacher(request,response);
                break;
        }
    }

    private void addNewTeacher(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Enter method addNewTeacher()");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Role role = Role.TEACHER;

        User user = new User(login,password, email, role);
        userService.createUser(user);

        try {
            response.sendRedirect("coursesList");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
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
            case "/logout":
                request.getRequestDispatcher("WEB-INF/view/logout.jsp").forward(request, response);
                break;
            case "/addTeacher":
                request.getRequestDispatcher("WEB-INF/view/addTeacher.jsp").forward(request, response);
                break;
            case "/accounts":
                listAccounts(request,response);
                break;
            default:
                response.sendRedirect("error");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Enter method logout()");
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("logout");

            logger.info("Redirect to logout.jsp. Close session, redirect to login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        boolean isActive = true;

        if(!userService.findUserByLogin(login)) {

            User user = new User(login, password, email, Role.STUDENT, isActive);
            logger.debug("Enter method insertUser: login:{}  email:{} ", login, email);

            try {
                if (userService.createUser(user)) {
                    response.sendRedirect("login");
                    request.setAttribute("error","");
                }
                else {
                    response.sendRedirect("registration");
                    request.setAttribute("error","Registration data is incorrect!");
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
        }
        else {
            try {
                request.setAttribute("error", "User with this username already exists!");
                request.getRequestDispatcher("WEB-INF/view/registration.jsp").forward(request, response);

            } catch (IOException | ServletException e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
        }
    }

    private void checkUserByLoginAndPswd(HttpServletRequest request, HttpServletResponse response) {


        String login = request.getParameter("login");
        String password = request.getParameter("password");
        logger.debug("Method checkUserByLogin: login:{} ", login);

        try {
            if (userService.findUserByLoginAndPswd(login, password)) {

                HttpSession session = request.getSession(true);
                session.setAttribute("userName", login);
                session.setAttribute("role",userService.getCurrentUserRole(login));
                logger.info("Opened session, logged In.");


                response.sendRedirect("coursesList");
                request.setAttribute("error", "");
            }
            else {
                request.setAttribute("error","Registration data is incorrect!");
                request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (ServletException | SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    private void listAccounts (HttpServletRequest request, HttpServletResponse response) {

        logger.info("Enter method listAccounts()");

        try {
            request.setAttribute("accounts",userService.listAccounts());
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/accounts.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }


}
