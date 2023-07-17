package com.example.carservice.repository;

import com.example.carservice.entity.Jobs;
import com.example.carservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobsRepository extends JpaRepository<Jobs, Long> {
}
