package com.example.carservice.service;

import com.example.carservice.entity.Jobs;
import com.example.carservice.entity.User;
import com.example.carservice.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobsService {
    @Autowired
    private JobsRepository jobsRepository;


    public List<Jobs> getAllJobs() {
        return jobsRepository.findAll();
    }

    public Optional<Jobs> getJobById(Long id) {
        return jobsRepository.findById(id);
    }

    public Jobs createJob(Jobs jobs) {
        return jobsRepository.save(jobs);
    }

    public List<Jobs> getAllJobsByUser(User user) {
        return jobsRepository.findAllByUsers(user);
    }
}
