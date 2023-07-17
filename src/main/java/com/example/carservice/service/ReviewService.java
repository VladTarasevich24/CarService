package com.example.carservice.service;

import com.example.carservice.entity.Review;
import com.example.carservice.entity.User;
import com.example.carservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;


    public Review createReview(String text, User author) {
        Review review = new Review();
        review.setText(text);
        review.setAuthor(author);
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}
