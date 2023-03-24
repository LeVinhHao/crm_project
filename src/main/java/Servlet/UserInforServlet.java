package Servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserInforServlet", urlPatterns = {"/user_infor"})
public class UserInforServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");
        UserService userService = new UserService();
        req.setAttribute("users", userService.getUserById(userService.getUserIdByEmailAndPass(email,password)));
        req.setAttribute("users_infor", userService.getUserDetailById(userService.getUserIdByEmailAndPass(email,password)));
        req.getRequestDispatcher("user-infor.jsp").forward(req,resp);
    }
}
