package repository;

import config.MysqlConfig;
import models.RolesModel;
import models.TaskModel;
import models.UsersModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public List<UsersModel>  getAllUsers(){
        List<UsersModel> listUsers = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select u.id, u.email, u.password, u.fullname, u.avatar, r.description \n" +
                "from users u , roles r\n" +
                "where u.role_id= r.id";
        try{
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()){
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getInt("id"));
                usersModel.setFullName(resultSet.getString("fullname"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setEmail(resultSet.getString("email"));
                usersModel.setPassword(resultSet.getString("password"));
                usersModel.setRoleDes(resultSet.getString("description"));

                listUsers.add(usersModel);

            }
        }catch (Exception e){
            System.out.println("Error get data from users "+e.getMessage());
        }
        return listUsers;
    }
    public int counUserByEmailAndPassword(String email, String password){
        Connection connection = MysqlConfig.getConnection();
        int count =0;
//       String query = "select coun(*) from user u where u.email = "+email+" and password="+password+";";
        String query = "select count(*) as count from users u\n" +
                "where u.email = ? and password=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.setString(2,password);
            ResultSet resultSet= statement.executeQuery();

            while (resultSet.next()){
                count=resultSet.getInt("count");

            }
            if(count>0){
                System.out.println("Dang nhap thanh cong");
            }else{
                System.out.println("Dang nhap that bai");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return  count;
    }

    public int checkRole(String email, String password){
        int roleId=0;
        Connection connection = MysqlConfig.getConnection();
        int count =0;
//       String query = "select coun(*) from user u where u.email = "+email+" and password="+password+";";
        String query = "select role_id from users u where u.email =? and password =?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.setString(2,password);
            ResultSet resultSet= statement.executeQuery();

            while (resultSet.next()){
                roleId=resultSet.getInt("role_id");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return roleId;
    }
    public int addNewUser(String email, String password, String fullname, String avatar, int roleId){
        int isSuccess=0;
        Connection connection = MysqlConfig.getConnection();
        String query = "INSERT INTO users(email, password,fullname,avatar,role_id ) VALUES (?,?,?,?,?);";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.setString(2,password);
            statement.setString(3,fullname);
            statement.setString(4,avatar);
            statement.setInt(5,roleId);
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
    public  int deleteUserById(int id){
        int isDelete=0;
        Connection connection = MysqlConfig.getConnection();
        String query = "delete from users u where u.id=?";
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
    public List<UsersModel>  getUserById(int id){
        List<UsersModel> listUsers = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select * from users where id=?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getInt("id"));
                usersModel.setFullName(resultSet.getString("fullname"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setEmail(resultSet.getString("email"));
                usersModel.setPassword(resultSet.getString("password"));

                listUsers.add(usersModel);

            }
        }catch (Exception e){
            System.out.println("Error get data from users "+e.getMessage());
        }
        return listUsers;
    }
    public int updateUser(int id, String email, String password, String fullname, String avatar, int roleId){
        int isSuccess=0;
        Connection connection = MysqlConfig.getConnection();
        String query = "update users set email=?, password=?,fullname=?,avatar=?,role_id=? where id=?;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.setString(2,password);
            statement.setString(3,fullname);
            statement.setString(4,avatar);
            statement.setInt(5,roleId);
            statement.setInt(6,id);
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

    public List<TaskModel>  getUserDetailById(int id){
        List<TaskModel> listTasks = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select t.name as task_name, t.start_date,t.end_date,u.email, u.fullname as username,s.id as status_id\n" +
                "from tasks t, users u, status s\n" +
                "where t.user_id = u.id and t.status_id= s.id and u.id=? ";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                TaskModel taskModel = new TaskModel();
                taskModel.setTaskName(resultSet.getString("task_name"));
                taskModel.setStartDate(resultSet.getDate("start_date"));
                taskModel.setEndDate(resultSet.getDate("end_date"));
                taskModel.setEmail(resultSet.getString("email"));
                taskModel.setFullname(resultSet.getString("username"));
                taskModel.setStatusId(resultSet.getInt("status_id"));

                listTasks.add(taskModel);

            }
        }catch (Exception e){
            System.out.println("Error get data from users "+e.getMessage());
        }
        return listTasks;
    }
    public List<UsersModel>  getUserByRoleUser(){
        List<UsersModel> listUsers = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select u.id, u.email, u.password, u.fullname, u.avatar, r.description \n" +
                "from users u , roles r\n" +
                "where u.role_id= r.id and u.role_id=3";
        try{
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()){
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getInt("id"));
                usersModel.setFullName(resultSet.getString("fullname"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setEmail(resultSet.getString("email"));
                usersModel.setPassword(resultSet.getString("password"));
                usersModel.setRoleDes(resultSet.getString("description"));
                listUsers.add(usersModel);

            }
        }catch (Exception e){
            System.out.println("Error get data from users "+e.getMessage());
        }
        return listUsers;
    }

    public int getUserIdByEmailAndPassword(String email, String password){
        Connection connection = MysqlConfig.getConnection();
        int id =0;
        String query = "select u.id from users u\n" +
                "where u.email = ? and u.password=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.setString(2,password);
            ResultSet resultSet= statement.executeQuery();

            while (resultSet.next()){
                id=resultSet.getInt("id");

            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return  id;
    }
}
