package service;

import models.StatusModel;
import models.TaskModel;
import models.UsersModel;
import repository.TaskRepository;
import repository.UserRepository;

import java.util.List;

public class TaskService {
    public List<TaskModel> getAllTasks() {
        TaskRepository taskRepository = new TaskRepository();
        return taskRepository.getAllTasks();
    }
    public boolean addNewTask(String name, String startDate, String endDate,int userId, int jobId, int statusId) {
        TaskRepository taskRepository = new TaskRepository();
        return taskRepository.addNewTask(name, startDate, endDate, userId, jobId,statusId) >= 1;
    }
    public boolean updateTask(int id,String name, String startDate, String endDate,int userId, int jobId,int statusId) {
        TaskRepository taskRepository = new TaskRepository();
        return taskRepository.updateTask(id,name,startDate,endDate,userId,jobId,statusId)>= 1;
    }
    public List<TaskModel> getTaskById(int id) {
        TaskRepository taskRepository = new TaskRepository();
        return taskRepository.getTaskById(id);
    }
    public boolean deleteTaskById(int id) {
        TaskRepository taskRepository = new TaskRepository();
        return taskRepository.deleteTaskById(id)>= 1;
    }
    public List<StatusModel> getStatus() {
        TaskRepository taskRepository = new TaskRepository();
        return taskRepository.getStatus();
    }
    public List<TaskModel> getTaskByRoleUser() {
        TaskRepository taskRepository = new TaskRepository();
        return taskRepository.getTaskRoleUser();
    }

    public List<TaskModel> getTaskByUserId(int id) {
        TaskRepository taskRepository = new TaskRepository();
        return taskRepository.getTaskByUserId(id);
    }
    public boolean updateTaskUser(int id,int statusId) {
        TaskRepository taskRepository = new TaskRepository();
        return taskRepository.updateTaskUser(id,statusId)>= 1;
    }
}
