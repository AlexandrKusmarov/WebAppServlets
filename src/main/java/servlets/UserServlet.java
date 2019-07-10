package servlets;

import model.Role;
import model.User;
import service.impl.CoursesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getServletPath();
        System.out.println("doPOST " + action);

        switch (action) {
            case "/registration" :
                try {
                    insertUser(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/login" :
                try {
                    loginUser(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        String action = request.getServletPath();
        System.out.println("doGET " + action);
        switch (action) {
            case "/registration" :
                    request.getRequestDispatcher("WEB-INF/view/registration.jsp").forward(request, response);
                break;
            case "/login" :
                    request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
                break;
        }
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        User user = new User(login,password,email, Role.STUDENT);

        if(new CoursesServiceImpl().createUser(user))
            response.sendRedirect("login");

    }

    private void loginUser (HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(new CoursesServiceImpl().findUser(login,password))
            response.sendRedirect("coursesList");
        else response.sendRedirect("login");
    }
}
