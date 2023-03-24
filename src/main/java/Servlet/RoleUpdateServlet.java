package Servlet;

import service.RolesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RoleUpdateServlet",urlPatterns = {"/update_role"})
public class RoleUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int id = Integer.parseInt(req.getParameter("id")) ;
        RolesService rolesService = new RolesService();
        req.setAttribute("roles", rolesService.getRoleById(id));
        req.getRequestDispatcher("role-update.jsp").forward(req,resp);
    }
}
