package servlets.filters;

import model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.impl.UserServiceImpl;
import servlets.UserServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class AuthorizationFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(UserServlet.class);
    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);
        String userName = (String) session.getAttribute("userName");
        Role role = (Role) session.getAttribute("role");

        logger.info("Enter doFilter() userName == {}", userName);

        try {
            if (userName == null || (Role.STUDENT == role && !userService.checkPermission(userName))) {
                request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
            }
            else {
                session.setAttribute("idUser",userService.getUserByLogin(userName).getIdUser());
                chain.doFilter(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }

}
