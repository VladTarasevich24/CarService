package com.example.carservice.repository;

import com.example.carservice.entity.UserJobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJobsRepository extends JpaRepository<UserJobs, Long> {

}
