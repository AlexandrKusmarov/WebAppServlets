package servlets;

import model.Role;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class UserServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(UserServlet.class);
    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
            case "/onOffAccount":
                changeAccountStatus(request, response);
                break;
            case "/profile":
                updateUser(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();
        logger.info("Enter method doGet, ACTION: {}", action);

        switch (action) {
            case "/":
                request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
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
            case "/profile":
                request.getRequestDispatcher("WEB-INF/view/profile.jsp").forward(request, response);
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
                session.setAttribute("password", userService.getUserByLogin(login).getPassword());
                session.setAttribute("mail", userService.getUserByLogin(login).getEmail());

                logger.info("Opened session, logged In.");

                response.sendRedirect("coursesList");
                request.setAttribute("error", "");
            }
            else {
                request.setAttribute("error","Registration data is incorrect!");
                request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
            }
        } catch (IOException | ServletException | SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
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

    private void changeAccountStatus(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("activity"));
        logger.info("Enter method changeAccountStatus(). Param: id={};",id);

        try {
            User user = userService.getUserById(id);
            String login = user.getLogin();
            String password = user.getPassword();
            String email = user.getEmail();
            String role = String.valueOf(user.getRole());
            boolean isActive = !user.isActive();

            userService.editUser(id, login, password, email, role, isActive);
            response.sendRedirect("accounts");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    private void getUserByLogin(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Enter method getUserByLogin()");
//        User user = null;
//        HttpSession session = request.getSession(false);
//        String login = (String)session.getAttribute ("userName");
//        try {
//            user = userService.getUserByLogin(login);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            logger.error(e.getMessage(), e);
//        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Enter method updateUser()");
        HttpSession session = request.getSession(false);
        String login = (String) session.getAttribute("userName");
        try {
            User user = userService.getUserByLogin(login);
            Long id = user.getIdUser();
            String newLogin = request.getParameter("login");
            String newPassword = request.getParameter("password");
            String newEmail = request.getParameter("mail");
            String role = String.valueOf(user.getRole());
            boolean isActive = user.isActive();
            userService.editUser(id,newLogin,newPassword,newEmail,role,isActive);

            response.sendRedirect("coursesList");
            request.setAttribute("updated", "Profile has been updated");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }
}
