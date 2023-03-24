package api;

import com.google.gson.Gson;
import models.RolesModel;
import models.TaskModel;
import pageload.BasicResponse;
import service.JobsService;
import service.RolesService;
import service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "TasksApi", urlPatterns = {"/api/tasks","/api/tasks/delete","/api/tasks/add","/api/tasks/update","/api/tasks/update_user"})
public class TasksApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        BasicResponse basicResponse = new BasicResponse();
        switch (url){
            case "/api/tasks/delete":
                int id= Integer.parseInt(req.getParameter("id"));
                basicResponse=deleteTaskById(id);
                break;
            case "/api/tasks":
                basicResponse=getAllTasks();
                resp.sendRedirect(req.getContextPath()+"/tasks");
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

    private BasicResponse deleteTaskById(int id){
        BasicResponse basicResponse = new BasicResponse();
        TaskService taskService = new TaskService();
        basicResponse.setStatusCode(200);
        basicResponse.setMessage("Xoa thanh cong");
        basicResponse.setData(taskService.deleteTaskById(id));
        return basicResponse;
    }
    private BasicResponse getAllTasks(){
        BasicResponse basicResponse= new BasicResponse();
        TaskService taskService =new TaskService();
        List<TaskModel> list= taskService.getAllTasks();
        basicResponse.setStatusCode(200);
        basicResponse.setData(list);
        return basicResponse;

    }
    private BasicResponse addTask(String name, String startDate, String endDate,int userId, int jobId,int statusId){
        BasicResponse basicResponse = new BasicResponse();
        TaskService taskService = new TaskService();
        basicResponse.setStatusCode(200);
        basicResponse.setMessage("them thanh cong");
        basicResponse.setData(taskService.addNewTask(name,startDate,endDate,userId,jobId,statusId));
        return basicResponse;
    }
    private BasicResponse updateTask(int id,String name, String startDate, String endDate,int userId, int jobId,int statusId){
        BasicResponse basicResponse = new BasicResponse();
        TaskService taskService = new TaskService();
        basicResponse.setStatusCode(200);
        basicResponse.setMessage("them thanh cong");
        basicResponse.setData(taskService.updateTask(id,name,startDate,endDate,userId,jobId,statusId));
        return basicResponse;
    }

    private BasicResponse updateTaskUser(int id,int statusId){
        BasicResponse basicResponse = new BasicResponse();
        TaskService taskService = new TaskService();
        basicResponse.setStatusCode(200);
        basicResponse.setMessage("them thanh cong");
        basicResponse.setData(taskService.updateTaskUser(id,statusId));
        return basicResponse;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getServletPath();
        BasicResponse  basicResponse = new BasicResponse();
        String jobName = req.getParameter("jobName");
        String taskName = req.getParameter("taskName");
        String userName = req.getParameter("userName");
        String startDate= req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String status = req.getParameter("status");


        int statusId= Integer.parseInt(status.substring(0,2).trim());


        switch (url){
            case "/api/tasks/add":
                try{
                    int jobId= Integer.parseInt(jobName.substring(0,2).trim());
                    int userId= Integer.parseInt(userName.substring(0,2).trim());
                    basicResponse=addTask(taskName,startDate,endDate,userId,jobId,statusId);
                    resp.sendRedirect(req.getContextPath()+"/tasks");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "/api/tasks/update" :
                int id = Integer.parseInt(req.getParameter("id"));
                int jobId= Integer.parseInt(jobName.substring(0,2).trim());
                int userId= Integer.parseInt(userName.substring(0,2).trim());
                basicResponse=updateTask(id,taskName,startDate,endDate,userId,jobId,statusId);
                resp.sendRedirect(req.getContextPath()+"/tasks");
                break;
            case "/api/tasks/update_user" :
                int idUser = Integer.parseInt(req.getParameter("id"));
                basicResponse=updateTaskUser(idUser,statusId);
                resp.sendRedirect(req.getContextPath()+"/tasks");
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
