package api;

import com.google.gson.Gson;
import models.RolesModel;
import models.UsersModel;
import pageload.BasicResponse;
import service.RolesService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UsersApi", urlPatterns = {"/api/user/adduser","/api/user/delete","/api/user/update_user","/api/user"})
public class UserApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        BasicResponse  basicResponse = new BasicResponse();
        switch (url){
            case "/api/user/delete":
                int id = Integer.parseInt(req.getParameter("id"));
                basicResponse=deleteUserById(id);
                break;
            case "/api/user":
                basicResponse=getAllUser();
                resp.sendRedirect(req.getContextPath()+"/user");
                break;
            default:
                basicResponse.setStatusCode(404);
                basicResponse.setMessage("Duong dan khong ton tai");
                break;
        }
        Gson gson = new Gson();
        String dataJson= gson.toJson(basicResponse);//convert doi tuong hoac mang ve json tuong ung

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(dataJson);
        printWriter.flush();
        printWriter.close();
    }
    private BasicResponse deleteUserById(int id){
        BasicResponse basicResponse = new BasicResponse();
        UserService userService = new UserService();
        basicResponse.setStatusCode(200);
        basicResponse.setMessage("Xoa thanh cong");
        basicResponse.setData(userService.deleteUserById(id));
        return basicResponse;
    }

    private BasicResponse getAllUser(){
        BasicResponse basicResponse= new BasicResponse();
        UserService userService =new UserService();
        List<UsersModel> list= userService.getAllUsers();
        basicResponse.setStatusCode(200);
        basicResponse.setData(list);
        return basicResponse;

    }

    private BasicResponse addNewUser(String email, String password, String fullname, String avatar, int roleId){
        BasicResponse basicResponse = new BasicResponse();
        UserService userService = new UserService();
        basicResponse.setStatusCode(200);
        basicResponse.setMessage("them thanh cong");
        basicResponse.setData(userService.addNewUser(email,password,fullname,avatar,roleId));
        return basicResponse;
    }
    private BasicResponse updateUser(int id, String email, String password, String fullname, String avatar, int roleId){
        BasicResponse basicResponse = new BasicResponse();
        UserService userService = new UserService();
        basicResponse.setStatusCode(200);
        basicResponse.setMessage("them thanh cong");
        basicResponse.setData(userService.updateUser(id,email,password,fullname,avatar,roleId));
        return basicResponse;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String roleName = req.getParameter("roleName");
        String avatar="";

        int roleId= Integer.parseInt(roleName.substring(0,2).trim());

        String url=req.getServletPath();
        BasicResponse  basicResponse = new BasicResponse();

        switch (url){
            case "/api/user/adduser":
                basicResponse=addNewUser(email,password,fullname,avatar,roleId);
                resp.sendRedirect(req.getContextPath()+"/user");
                break;
            case "/api/user/update_user" :
                int id =Integer.parseInt(req.getParameter("id"));
                basicResponse=updateUser(id,email,password,fullname,avatar,roleId);
                resp.sendRedirect(req.getContextPath()+"/user");
                break;
            default:
                basicResponse.setStatusCode(404);
                basicResponse.setMessage("Duong dan khong ton tai");
                break;
        }

        Gson gson = new Gson();
        String dataJson= gson.toJson(basicResponse);//convert doi tuong hoac mang ve json tuong ung

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(dataJson);
        printWriter.flush();
        printWriter.close();
    }
}
