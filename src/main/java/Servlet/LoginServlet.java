package Servlet;

import config.MysqlConfig;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet",urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        Cookie cookie = new Cookie("username","hao");
//        cookie.setMaxAge(60*60*8);
//        resp.addCookie(cookie); //yeu cau client tao cookie

//        Cookie []cookies =req.getCookies();
//        for(Cookie c: cookies){
//            String name= c.getName();
//            if(name.equals("username")){
//                System.out.println("Name: "+c.getValue());
//            }
//        }

//        HttpSession session = req.getSession();
//        session.setAttribute("username","Le Vinh Hao");
//        session.setMaxInactiveInterval(8*60*60*1000); //*1000= miliseconds
//        String data=(String) session.getAttribute("username");
//        System.out.println("username"+data);




//        Connection connection= MysqlConfig.getConnection();
//        String query= "select*from users";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                String email= resultSet.getString("email");
//                String password = resultSet.getString("password");
//                int roleId= resultSet.getInt("role_id");
//                System.out.println(email);
//                System.out.println(password);
//            }
//        } catch (SQLException e) {
//            System.out.println("Loi thuc thi cau truy van login"+e.getMessage());
//        }finally {
//            if(connection!=null){
//                try{
//                    connection.close();
//                }catch (SQLException e){
//                    throw new RuntimeException(e);
//                }
//            }
//        }
        req.getRequestDispatcher("login.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String email = req.getParameter("email");
       String password = req.getParameter("password");

        LoginService loginService = new LoginService();
        boolean isSuccess = loginService.checkLogin(email,password);
        int roleId= loginService.checkRole(email,password);
        System.out.println("kiem tra = "+isSuccess);


        if(isSuccess){
            HttpSession session = req.getSession();
            session.setAttribute("roleId",roleId);
            session.setAttribute("email",email);
            session.setAttribute("password",password);
            resp.sendRedirect(req.getContextPath()+"/tasks");
        }else{
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
