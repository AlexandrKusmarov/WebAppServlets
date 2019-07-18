package servlets.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlets.UserServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(UserServlet.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);
        String userName = (String) session.getAttribute("userName");

        if (userName == null) {
            res.sendRedirect("login");
            logger.info("Enter doFilter() userName == null");
        } else {
            chain.doFilter(request, response);
            logger.info("Enter doFilter() session != null");

        }
    }

    @Override
    public void destroy() {

    }

}
