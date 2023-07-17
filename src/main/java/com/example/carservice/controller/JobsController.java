package com.example.carservice.controller;

import com.example.carservice.entity.Jobs;
import com.example.carservice.entity.User;
import com.example.carservice.repository.UserRepository;
import com.example.carservice.service.JobsService;
import com.example.carservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/jobs")
public class JobsController {

    @Autowired
    private JobsService jobsService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

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
        Jobs jobs = jobsOptional.get();
        model.addAttribute("job", jobs);
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

    @PostMapping("/{id}/signup")
    @Transactional
    public String signUpForJob(@PathVariable("id") Long jobId, Authentication authentication) {
        String username = authentication.getName();
        Optional<User> userOptional = userService.getUserByUsername(username);
        Optional<Jobs> jobsOptional = jobsService.getJobById(jobId);
        if (userOptional.isPresent() && jobsOptional.isPresent()) {
            User user = userOptional.get();
            Jobs jobs = jobsOptional.get();
            user.getJobs().add(jobs);
            System.out.println(user);
            userService.update(user);
            return "redirect:/";
        }
        return "redirect:/";
    }
    @PostMapping("/{id}/delete")
    public String deleteJob(@PathVariable("id") Long jobId) {
        jobsService.deleteJob(jobId);
        return "redirect:/jobs/allJobs";
    }
    @GetMapping("/{id}/edit")
    public String showEditJobForm(@PathVariable("id") Long jobId, Model model) {
        Optional<Jobs> jobsOptional = jobsService.getJobById(jobId);
        if (jobsOptional.isPresent()) {
            Jobs job = jobsOptional.get();
            model.addAttribute("job", job);
            return "editJob";
        }
        return "redirect:/jobs/allJobs";
    }

    @PostMapping("/{id}/edit")
    public String updateJob(@PathVariable("id") Long jobId, @ModelAttribute("job") Jobs updatedJob) {
        Optional<Jobs> jobsOptional = jobsService.getJobById(jobId);
        if (jobsOptional.isPresent()) {
            Jobs job = jobsOptional.get();
            job.setName(updatedJob.getName());
            job.setDescription(updatedJob.getDescription());
            job.setCost(updatedJob.getCost());
            jobsService.createJob(job);
        }
        return "redirect:/jobs/allJobs";
    }

}


