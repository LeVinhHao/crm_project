package service;

import models.RolesModel;
import repository.RolesRepository;
import repository.UserRepository;

import java.util.List;

public class RolesService {
    public List<RolesModel> getAllRoles(){
        RolesRepository rolesRepository = new RolesRepository();
        return rolesRepository.getAllRoles();
    }

    public  boolean deleteRoleById(int id){
        RolesRepository rolesRepository= new RolesRepository();
        return rolesRepository.deleteRolesById(id)>=1;
    }

    public boolean addNewRole(String name, String description){
        RolesRepository rolesRepository = new RolesRepository();
        return rolesRepository.addNewRole(name,description)>=1;
    }
    public List<RolesModel> getRoleById(int id){
        RolesRepository rolesRepository = new RolesRepository();
        return rolesRepository.getRoleById(id);
    }

    public boolean updateRole(int id,String name, String description) {
        RolesRepository rolesRepository = new RolesRepository();
        return rolesRepository.updateRole(id,name,description)>= 1;
    }
}
