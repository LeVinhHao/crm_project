package repository;

import config.MysqlConfig;
import models.StatusModel;
import models.TaskModel;
import models.UsersModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    public List<TaskModel> getAllTasks(){
        List<TaskModel> liskTasks = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select t.id, t.name as task_name, t.start_date,t.end_date,u.fullname as username,j.name as job_name,s.name as status_name\n" +
                "from tasks t, users u, jobs j, status s\n" +
                "where t.user_id = u.id and t.job_id=j.id and t.status_id= s.id";
        try{
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()){
                TaskModel taskModel = new TaskModel();
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setTaskName(resultSet.getString("task_name"));
                taskModel.setStartDate(resultSet.getDate("start_date"));
                taskModel.setEndDate(resultSet.getDate("end_date"));
                taskModel.setFullname(resultSet.getString("username"));
                taskModel.setJobName(resultSet.getString("job_name"));
                taskModel.setStatusName(resultSet.getString("status_name"));

                liskTasks.add(taskModel);

            }
        }catch (Exception e){
            System.out.println("Error get data from users "+e.getMessage());
        }
        return liskTasks;
    }
    public int addNewTask(String name, String startDate, String endDate,int userId, int jobId,int statusId){
        int isSuccess=0;
        Connection connection = MysqlConfig.getConnection();
        String query = "INSERT INTO tasks(name, start_date,end_date,user_id,job_id,status_id ) VALUES (?,?,?,?,?,?);";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setDate(2, Date.valueOf(startDate));
            statement.setDate(3,Date.valueOf(endDate));
            statement.setInt(4,userId);
            statement.setInt(5,jobId);
            statement.setInt(6,statusId);
            isSuccess= statement.executeUpdate();

        }catch (Exception e){
            System.out.println("Error Insert query"+e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e){

            }
        }
        return isSuccess;
    }

    public int updateTask(int id,String name, String startDate, String endDate,int userId, int jobId,int statusId){
        int isSuccess=0;
        Connection connection = MysqlConfig.getConnection();
        String query = "update tasks set name=?, start_date=?,end_date=?,user_id=?,job_id=?, status_id=? where id=?;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setDate(2,Date.valueOf(startDate));
            statement.setDate(3,Date.valueOf(endDate));
            statement.setInt(4,userId);
            statement.setInt(5,jobId);
            statement.setInt(6,statusId);
            statement.setInt(7,id);
            isSuccess= statement.executeUpdate();

        }catch (Exception e){
            System.out.println("Error Insert query"+e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e){

            }
        }
        return isSuccess;
    }

    public List<TaskModel>  getTaskById(int id){
        List<TaskModel> listTasks = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select t.id, t.name as task_name, t.start_date,t.end_date,u.fullname as username,j.name as job_name,s.name as status_name\n" +
                "from tasks t, users u, jobs j, status s\n" +
                "where t.user_id = u.id and t.job_id=j.id and t.status_id= s.id and t.id=?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                TaskModel taskModel = new TaskModel();
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setTaskName(resultSet.getString("task_name"));
                taskModel.setStartDate(resultSet.getDate("start_date"));
                taskModel.setEndDate(resultSet.getDate("end_date"));
                taskModel.setFullname(resultSet.getString("username"));
                taskModel.setJobName(resultSet.getString("job_name"));
                taskModel.setStatusName(resultSet.getString("status_name"));

                listTasks.add(taskModel);

            }
        }catch (Exception e){
            System.out.println("Error get data from users "+e.getMessage());
        }
        return listTasks;
    }

    public  int deleteTaskById(int id){
        int isDelete=0;
        Connection connection = MysqlConfig.getConnection();
        String query = "delete from tasks t where t.id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            isDelete= statement.executeUpdate();

        }catch (Exception e){
            System.out.println("Error delete by id"+e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e){

            }
        }
        return isDelete;
    }
    public List<StatusModel> getStatus(){
        List<StatusModel> liskStatus = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select * from status";
        try{
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()){
                StatusModel statusModel = new StatusModel();
                statusModel.setId(resultSet.getInt("id"));
                statusModel.setName(resultSet.getString("name"));

                liskStatus.add(statusModel);

            }
        }catch (Exception e){
            System.out.println("Error get data from status "+e.getMessage());
        }
        return liskStatus;
    }

    public List<TaskModel>  getTaskRoleUser(){
        List<TaskModel> listTasks = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select t.id, t.name as task_name, t.start_date,t.end_date,u.fullname as username,j.name as job_name,s.name as status_name\n" +
                "from tasks t, users u, jobs j, status s\n" +
                "where t.user_id = u.id and t.job_id=j.id and t.status_id= s.id and u.role_id =3";
        try{
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()){
                TaskModel taskModel = new TaskModel();
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setTaskName(resultSet.getString("task_name"));
                taskModel.setStartDate(resultSet.getDate("start_date"));
                taskModel.setEndDate(resultSet.getDate("end_date"));
                taskModel.setFullname(resultSet.getString("username"));
                taskModel.setJobName(resultSet.getString("job_name"));
                taskModel.setStatusName(resultSet.getString("status_name"));

                listTasks.add(taskModel);

            }
        }catch (Exception e){
            System.out.println("Error get data from users "+e.getMessage());
        }
        return listTasks;
    }
    public List<TaskModel>  getTaskByUserId(int id){
        List<TaskModel> listTasks = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select t.id, t.name as task_name, t.start_date,t.end_date,u.fullname as username,j.name as job_name,s.name as status_name\n" +
                "from tasks t, users u, jobs j, status s\n" +
                "where t.user_id = u.id and t.job_id=j.id and t.status_id= s.id and u.id=?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                TaskModel taskModel = new TaskModel();
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setTaskName(resultSet.getString("task_name"));
                taskModel.setStartDate(resultSet.getDate("start_date"));
                taskModel.setEndDate(resultSet.getDate("end_date"));
                taskModel.setFullname(resultSet.getString("username"));
                taskModel.setJobName(resultSet.getString("job_name"));
                taskModel.setStatusName(resultSet.getString("status_name"));

                listTasks.add(taskModel);

            }
        }catch (Exception e){
            System.out.println("Error get data from users "+e.getMessage());
        }
        return listTasks;
    }

    public int updateTaskUser(int id,int statusId){
        int isSuccess=0;
        Connection connection = MysqlConfig.getConnection();
        String query = "update tasks set status_id=? where id=?;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,statusId);
            statement.setInt(2,id);
            isSuccess= statement.executeUpdate();

        }catch (Exception e){
            System.out.println("Error Insert query"+e.getMessage());
        }finally {
            try {
                connection.close();
            }catch (Exception e){

            }
        }
        return isSuccess;
    }
}
