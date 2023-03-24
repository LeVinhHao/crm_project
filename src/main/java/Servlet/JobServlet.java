package Servlet;

import service.JobsService;
import service.RolesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "JobServlet",urlPatterns = {"/jobs"})
public class JobServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JobsService jobsService = new JobsService();
        req.setAttribute("jobs", jobsService.getAllJobs());
        req.getRequestDispatcher("groupwork.jsp").forward(req,resp);
    }
}
