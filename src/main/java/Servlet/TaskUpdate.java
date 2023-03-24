package Servlet;

import service.JobsService;
import service.TaskService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/update_task"})
public class TaskUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        TaskService taskService = new TaskService();
        JobsService jobsService = new JobsService();
        UserService userService = new UserService();
        req.setAttribute("tasks", taskService.getTaskById(id));
        req.setAttribute("jobs", jobsService.getAllJobs());
        req.setAttribute("users", userService.getAllUsers());
        req.setAttribute("status", taskService.getStatus());
        req.setAttribute("users_manager", userService.getUserByRoleUser());

        req.getRequestDispatcher("task-update.jsp").forward(req,resp);
    }
}
