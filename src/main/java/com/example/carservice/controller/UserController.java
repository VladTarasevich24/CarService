package com.example.carservice.controller;


import com.example.carservice.AuthenticationFacade;
import com.example.carservice.dto.UserRegistrationDto;
import com.example.carservice.entity.Car;
import com.example.carservice.entity.Jobs;
import com.example.carservice.entity.User;
import com.example.carservice.repository.UserRepository;
import com.example.carservice.service.JobsService;
import com.example.carservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JobsService jobsService;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/reg")
    public String reg() {
        return "reg";
    }



    @PostMapping("/reg")
    public String reg(UserRegistrationDto dto, Model model) {
        User user = modelMapper.map(dto, User.class);
        Car car = modelMapper.map(dto, Car.class);
        user.setCar(car);
        userService.create(user);
        return "redirect:/";
    }
    @GetMapping("/info")
    public String getUserInfo(Model model) {
        String username = authenticationFacade.getUsername();
        Optional<User> userOptional = userService.getUserByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            List<Jobs> userJobs = jobsService.getAllJobsByUser(user);
            model.addAttribute("userJobs", userJobs);
            return "info";
        }
        return "redirect:/";
    }

@GetMapping("/all")
public String getAllUsers(Model model) {
    List<User> users = userService.getAllUsers();
    model.addAttribute("users", users);
    return "user-list";
}
    @PostMapping("/status/{id}")
    public String updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        userRepository.updateUserStatus(id, status);
        return "redirect:/user/all";
    }

}
