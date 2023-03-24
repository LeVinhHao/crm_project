package repository;

import config.MysqlConfig;
import models.RolesColumn;
import models.RolesModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RolesRepository {
    public List<RolesModel> getAllRoles(){
        List<RolesModel> listRoles= new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select * from roles";
        try{
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            while (resultSet.next()){
                RolesModel rolesModel = new RolesModel();
//                rolesModel.setId(resultSet.getInt(RolesColumn.ID.getValue()));
//                rolesModel.setRoleName(resultSet.getString(RolesColumn.NAME.getValue()));
//                rolesModel.setRoleDecription(resultSet.getString(RolesColumn.DESCRIPTION.getValue()));
                rolesModel.setId(resultSet.getInt("id"));
                rolesModel.setRoleName(resultSet.getString("name"));
                rolesModel.setRoleDecription(resultSet.getString("description"));
                listRoles.add(rolesModel);

            }
        }catch (Exception e){
            System.out.println("lỗi "+e.getMessage());
        }
        return listRoles;
    }


    public  int deleteRolesById(int id){
        int isDelete=0;
        Connection connection = MysqlConfig.getConnection();
        String query = "delete from roles r where r.id=?";
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
    public int addNewRole(String name, String description){
        int isSuccess=0;
        Connection connection = MysqlConfig.getConnection();
        String query = "INSERT INTO roles( name, description ) VALUES (?,?);";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2,description);
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
    public List<RolesModel> getRoleById( int id){
        List<RolesModel> listRoles= new ArrayList<>();
        Connection connection = MysqlConfig.getConnection();
        String query = "select * from roles where id=?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                RolesModel rolesModel = new RolesModel();
                rolesModel.setId(resultSet.getInt("id"));
                rolesModel.setRoleName(resultSet.getString("name"));
                rolesModel.setRoleDecription(resultSet.getString("description"));
                listRoles.add(rolesModel);

            }
        }catch (Exception e){
            System.out.println("lỗi getRoleById"+e.getMessage());
        }
        return listRoles;
    }
    public int updateRole(int id, String name, String description){
        int isSuccess=0;
        Connection connection = MysqlConfig.getConnection();
        String query = "update roles set name=?, description=? where id=?;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setString(2,description);
            statement.setInt(3,id);
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
