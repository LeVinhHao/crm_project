package service;

import models.RolesModel;
import models.TaskModel;
import models.UsersModel;
import repository.RolesRepository;
import repository.UserRepository;

import java.util.List;

public class UserService {
    public List<UsersModel> getAllUsers() {
        UserRepository userRepository = new UserRepository();
        return userRepository.getAllUsers();
    }

    public boolean addNewUser(String email, String password, String fullname, String avatar, int roleId) {
        UserRepository userRepository = new UserRepository();
        return userRepository.addNewUser(email, password, fullname, avatar, roleId) >= 1;
    }

    public boolean deleteUserById(int id) {
        UserRepository userRepository = new UserRepository();
        return userRepository.deleteUserById(id)>= 1;
    }
    public List<UsersModel> getUserById(int id) {
        UserRepository userRepository = new UserRepository();
        return userRepository.getUserById(id);
    }
    public boolean updateUser(int id,String email, String password, String fullname, String avatar, int roleId) {
        UserRepository userRepository = new UserRepository();
        return userRepository.updateUser(id,email,password,fullname,avatar,roleId)>= 1;
    }
    public List<TaskModel> getUserDetailById(int id) {
        UserRepository userRepository = new UserRepository();
        return userRepository.getUserDetailById(id);
    }
    public List<UsersModel> getUserByRoleUser() {
        UserRepository userRepository = new UserRepository();
        return userRepository.getUserByRoleUser();
    }
    public int getUserIdByEmailAndPass(String email,String password) {
        UserRepository userRepository = new UserRepository();
        return userRepository.getUserIdByEmailAndPassword(email,password);
    }
}