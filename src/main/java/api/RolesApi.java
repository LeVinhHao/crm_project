package api;

import com.google.gson.Gson;
import models.RolesModel;
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

@WebServlet(name = "RolesApi", urlPatterns = {"/api/roles","/api/roles/delete","/api/roles/add","/api/roles/update"})
public class RolesApi extends HttpServlet {

    //GET: select dung get
    //POST: ngoai select dung post
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        BasicResponse  basicResponse = new BasicResponse();
        switch (url){
            case "/api/roles/delete":
                int id = Integer.parseInt(req.getParameter("id"));
                basicResponse=deleteRoleById(id);
                break;
            case "/api/roles":
                basicResponse=getAllRole();
                resp.sendRedirect(req.getContextPath()+"/roles");
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
    private BasicResponse deleteRoleById(int id){
        BasicResponse basicResponse = new BasicResponse();
        RolesService rolesService = new RolesService();
        basicResponse.setStatusCode(200);
        basicResponse.setMessage("Xoa thanh cong");
        basicResponse.setData(rolesService.deleteRoleById(id));
        return basicResponse;
    }
    private BasicResponse getAllRole(){
        BasicResponse basicResponse= new BasicResponse();
        RolesService rolesService =new RolesService();
        List<RolesModel> list= rolesService.getAllRoles();
        basicResponse.setStatusCode(200);
        basicResponse.setData(list);
        return basicResponse;
    }
    private BasicResponse updateRole(int id, String name, String description){
        BasicResponse basicResponse = new BasicResponse();
        RolesService rolesService = new RolesService();
        basicResponse.setStatusCode(200);
        basicResponse.setMessage("them thanh cong");
        basicResponse.setData(rolesService.updateRole(id,name,description));
        return basicResponse;
    }
    private BasicResponse addNewRole(String name, String description){
        BasicResponse basicResponse = new BasicResponse();
        RolesService rolesService = new RolesService();
        basicResponse.setStatusCode(200);
        basicResponse.setMessage("them thanh cong");
        basicResponse.setData(rolesService.addNewRole(name,description));
        return basicResponse;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String url=req.getServletPath();
        BasicResponse  basicResponse = new BasicResponse();
        switch (url){
            case "/api/roles/add":
                basicResponse=addNewRole(name,description);
                resp.sendRedirect(req.getContextPath()+"/roles");
                break;
            case "/api/roles/update":
                int id =Integer.parseInt(req.getParameter("id")) ;
                basicResponse=updateRole(id,name,description);
                resp.sendRedirect(req.getContextPath()+"/roles");
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
