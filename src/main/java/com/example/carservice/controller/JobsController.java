package com.example.carservice.controller;

import com.example.carservice.entity.Jobs;
import com.example.carservice.entity.User;
import com.example.carservice.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jobs")
public class JobsController {

    @Autowired
    private JobsService jobsService;

    @GetMapping("/allJobs")
    public String getAllJobs(Model model) {
        List<Jobs> jobs = jobsService.getAllJobs();
        model.addAttribute("jobsList", jobs);
        return "allJobs";
    }
@GetMapping("/{id}")
public String getJobById(@PathVariable("id") Long id, Model model) {
    Optional<Jobs> jobsOptional = jobsService.getJobById(id);
    if (jobsOptional.isPresent()) {
        Jobs job = jobsOptional.get();
        model.addAttribute("job", job);
        return "jobInfo";
    }
    return "redirect:/";
}


    @GetMapping("/create")
    public String createJob() {
        return "createJob";
    }

    @PostMapping("/create")
    public String createJob(@ModelAttribute("jobs") Jobs jobs) {
        jobsService.createJob(jobs);
        return "redirect:/";
    }
}
