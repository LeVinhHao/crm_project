package Servlet;

import service.JobsService;
import service.RolesService;
import service.TaskService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TaskAddServlet",urlPatterns = {"/task_add"})
public class TaskAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JobsService jobsService = new JobsService();
        UserService userService = new UserService();
        TaskService taskService = new TaskService();
        req.setAttribute("jobs", jobsService.getAllJobs());
        req.setAttribute("users", userService.getAllUsers());
        req.setAttribute("users_manager", userService.getUserByRoleUser());
        req.setAttribute("status", taskService.getStatus());
        req.getRequestDispatcher("task-add.jsp").forward(req,resp);
    }
}
