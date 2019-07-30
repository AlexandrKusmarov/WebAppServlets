package servlets.filters;

import model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlets.UserServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessControl implements Filter {

    private static Logger logger = LoggerFactory.getLogger(AccessControl.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("Enter method doFilter()");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        Long id = (Long) session.getAttribute("idUser");
        Role role = (Role) session.getAttribute("role");
        String path = ((HttpServletRequest) request).getServletPath();

        if(role == Role.ADMIN) {
            logger.debug("Role = 'ADMIN'. Filter passed.");
            chain.doFilter(request, response);
        }
        else if (role == Role.STUDENT && path.equals("/profile") ||
                     role == Role.TEACHER && path.equals("/profile"))
        {
            logger.debug("Role = {} and servletPath =/profile . Filter passed.", role);
            chain.doFilter(request, response);
        }
         else {
            request.getRequestDispatcher("WEB-INF/error.jsp").forward(request, response);
            logger.error("User with id={} haven't permission.", id);
        }
    }

    @Override
    public void destroy() {

    }
}
