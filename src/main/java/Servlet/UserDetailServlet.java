package Servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserDetailServlet", urlPatterns = {"/user_details"})
public class UserDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =Integer.parseInt(req.getParameter("id"));

        UserService userService = new UserService();
        req.setAttribute("users", userService.getUserById(id));
        req.setAttribute("users_details", userService.getUserDetailById(id));
        req.getRequestDispatcher("user-details.jsp").forward(req,resp);
    }
}
