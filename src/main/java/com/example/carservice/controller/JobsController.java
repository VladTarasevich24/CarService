package com.example.carservice.controller;

import com.example.carservice.entity.Jobs;
import com.example.carservice.entity.User;
import com.example.carservice.entity.UserJobs;
import com.example.carservice.repository.UserJobsRepository;
import com.example.carservice.repository.UserRepository;
import com.example.carservice.service.JobsService;
import com.example.carservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    @Autowired
    private UserService userService;
    @Autowired
    private UserJobsRepository userJobsRepository;
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
    @PostMapping("/{id}/signup")
    public String signUpForJob(@PathVariable("id") Long jobId, Authentication authentication) {
        String username = authentication.getName();
        Optional<User> userOptional = userService.getUserByUsername(username);
        Optional<Jobs> jobsOptional = jobsService.getJobById(jobId);

        if (userOptional.isPresent() && jobsOptional.isPresent()) {
            User user = userOptional.get();
            Jobs jobs = jobsOptional.get();
            UserJobs userJobs = new UserJobs();
            userJobs.setUser(user);
            userJobs.setJobs(jobs);
            userJobsRepository.save(userJobs);
            user.setJob(jobs);
            userRepository.save(user);

            return "redirect:/";
        }

        return "redirect:/jobs";
    }

}
