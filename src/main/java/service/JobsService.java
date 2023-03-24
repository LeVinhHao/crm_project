package service;

import models.JobsModel;
import models.UsersModel;
import repository.JobsRepository;
import repository.RolesRepository;
import repository.UserRepository;

import java.util.Date;
import java.util.List;

public class JobsService {
    public List<JobsModel> getAllJobs(){
        JobsRepository jobsRepository = new JobsRepository();
        return jobsRepository.getAllJobs();
    }
    public boolean deleteJobsById(int id){
        JobsRepository jobsRepository = new JobsRepository();
        return jobsRepository.deleteJobsById(id)>=1;
    }

    public boolean addJob(String name, String startDate, String endDate){
        JobsRepository jobsRepository = new JobsRepository();
        return jobsRepository.addJob(name,startDate,endDate)>=1;
    }
    public List<JobsModel> getJobById(int id) {
        JobsRepository jobsRepository = new JobsRepository();
        return jobsRepository.getJobById(id);
    }
    public boolean updateJob(int id,String name, String startDate , String endDate) {
        JobsRepository jobsRepository = new JobsRepository();
        return jobsRepository.updateJob(id,name,startDate,endDate)>= 1;
    }
}
