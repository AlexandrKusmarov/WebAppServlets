package servlets;

import model.Courses;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        req.getRequestDispatcher("WEB-INF/view/coursesList.jsp").forward(req, resp);
                String action = req.getServletPath();
        System.out.println(action);
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
        List<Courses> coursesList = new CoursesServiceImpl().getAllCourses();

        request.setAttribute("coursesList", coursesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/coursesList.jsp");
        dispatcher.forward(request,response);
    }

}
