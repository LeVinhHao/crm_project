package api;

import com.google.gson.Gson;
import models.RolesModel;
import pageload.BasicResponse;
import service.JobsService;
import service.RolesService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "JobssApi", urlPatterns = {"/api/jobs","/api/jobs/delete","/api/jobs/add","/api/jobs/update"})
public class JobsApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        BasicResponse basicResponse = new BasicResponse();
        switch (url){
            case "/api/jobs/delete":
                int id= Integer.parseInt(req.getParameter("id"));
                basicResponse=deleteJobsById(id);
                break;
            case "/api/jobs":
                basicResponse=getAllJobs();
                resp.sendRedirect(req.getContextPath()+"/jobs");
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

    private BasicResponse deleteJobsById(int id){
        BasicResponse basicResponse = new BasicResponse();
        JobsService jobsService = new JobsService();
        basicResponse.setStatusCode(200);
        basicResponse.setMessage("Xoa thanh cong");
        basicResponse.setData(jobsService.deleteJobsById(id));
        return basicResponse;
    }
    private BasicResponse getAllJobs(){
        BasicResponse basicResponse= new BasicResponse();
        RolesService rolesService =new RolesService();
        List<RolesModel> list= rolesService.getAllRoles();
        basicResponse.setStatusCode(200);
        basicResponse.setData(list);
        return basicResponse;

    }
    private BasicResponse addJob(String name, String startDate, String endDate){
        BasicResponse basicResponse = new BasicResponse();
        JobsService jobsService = new JobsService();
        basicResponse.setStatusCode(200);
        basicResponse.setMessage("them thanh cong");
        basicResponse.setData(jobsService.addJob(name,startDate,endDate));
        return basicResponse;
    }
    private BasicResponse updateJob(int id,String name, String startDate, String endDate){
        BasicResponse basicResponse = new BasicResponse();
        JobsService jobsService = new JobsService();
        basicResponse.setStatusCode(200);
        basicResponse.setMessage("them thanh cong");
        basicResponse.setData(jobsService.updateJob(id,name,startDate,endDate));
        return basicResponse;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        BasicResponse  basicResponse = new BasicResponse();
        String name = req.getParameter("name");
        String startDate= req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        switch (url){
            case "/api/jobs/add":

                try{
                    basicResponse=addJob(name,startDate,endDate);
                    resp.sendRedirect(req.getContextPath()+"/jobs");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "/api/jobs/update" :
                int id =Integer.parseInt(req.getParameter("id"));
                basicResponse=updateJob(id,name,startDate,endDate);
                resp.sendRedirect(req.getContextPath()+"/jobs");
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
