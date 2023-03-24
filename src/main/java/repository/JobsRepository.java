package repository;

import config.MysqlConfig;
import models.JobsModel;
import models.UsersModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobsRepository {
    public List<JobsModel> getAllJobs(){
        List<JobsModel> listJobs= new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select * from jobs";
        try{
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()){
                JobsModel jobsModel = new JobsModel();
                jobsModel.setId(resultSet.getInt("id"));
                jobsModel.setName(resultSet.getString("name"));
                jobsModel.setStartDate(resultSet.getDate("start_date"));
                jobsModel.setEndDate(resultSet.getDate("end_date"));
                listJobs.add(jobsModel);

            }
        }catch (Exception e){
            System.out.println("lỗi "+e.getMessage());
        }
        return listJobs;
    }
    public  int deleteJobsById(int id){
        int isDelete=0;
        Connection connection = MysqlConfig.getConnection();
        String query = "delete from jobs r where r.id=?";
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

    public int addJob(String name, String startDate, String endDate){

        int isSuccess=0;
        Connection connection = MysqlConfig.getConnection();
        String query = "INSERT INTO jobs(name,start_date,end_date) VALUES (?,?,?);";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setDate(2,Date.valueOf(startDate));
            statement.setDate(3,Date.valueOf(endDate));
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
    public List<JobsModel>  getJobById(int id){
        List<JobsModel> listJobs = new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select * from jobs where id=?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                JobsModel jobsModel = new JobsModel();
                jobsModel.setId(resultSet.getInt("id"));
                jobsModel.setName(resultSet.getString("name"));
                jobsModel.setStartDate(resultSet.getDate("start_date"));
                jobsModel.setEndDate(resultSet.getDate("end_date"));
                listJobs.add(jobsModel);

            }
        }catch (Exception e){
            System.out.println("Error get data from users "+e.getMessage());
        }
        return listJobs;
    }
    public int updateJob(int id,String name, String startDate, String endDate){
        int isSuccess=0;
        Connection connection = MysqlConfig.getConnection();
        String query = "update jobs set name=?, start_date=?,end_date=? where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setDate(2,Date.valueOf(startDate));
            statement.setDate(3,Date.valueOf(endDate));
            statement.setInt(4,id);
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