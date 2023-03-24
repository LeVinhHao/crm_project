package Servlet;

import service.RolesService;
import service.TaskService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "TaskServlet",urlPatterns = {"/tasks"})
public class TaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");
        UserService userService = new UserService();


        TaskService taskService = new TaskService();
        req.setAttribute("tasks", taskService.getAllTasks());
        req.setAttribute("tasks_user", taskService.getTaskByRoleUser());
        req.setAttribute("tasks_userId",taskService.getTaskByUserId(userService.getUserIdByEmailAndPass(email,password)));
        req.getRequestDispatcher("task.jsp").forward(req,resp);
    }
}
