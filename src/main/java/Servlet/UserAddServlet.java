package Servlet;

import service.RolesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/adduser"})
public class UserAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RolesService rolesService = new RolesService();
        req.setAttribute("roles", rolesService.getAllRoles());
        req.getRequestDispatcher("user-add.jsp").forward(req,resp);
    }
}
