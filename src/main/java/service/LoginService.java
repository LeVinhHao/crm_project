package service;

import repository.UserRepository;

public class LoginService {
    public boolean checkLogin(String email, String password){
        UserRepository userRepository= new UserRepository();
        int count = userRepository.counUserByEmailAndPassword(email,password);
        return count>0;
    }
    public int checkRole(String email, String password){
        UserRepository userRepository= new UserRepository();
        return  userRepository.checkRole(email,password);
    }
}
