package servlets.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlets.UserServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(UserServlet.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);
        String userName = (String) session.getAttribute("userName");

        logger.info("Enter doFilter() userName == {}", userName);

        if (userName == null) {
            request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
