package com.example.carservice.controller;

import com.example.carservice.entity.Review;
import com.example.carservice.entity.User;
import com.example.carservice.service.ReviewService;
import com.example.carservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private final ReviewService reviewService;
    @Autowired
    private UserService userService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create")
    public String createReview(@RequestParam("text") String text, Authentication authentication) {
        String username = authentication.getName();
        User author = userService.getUserByUsername(username).orElse(null);
        if (author != null) {
            reviewService.createReview(text, author);
        }
        return "redirect:/reviews";
    }

    @GetMapping
    public String showReviews(Model model) {
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "reviews";
    }
}