package com.example.carservice.service;

import com.example.carservice.entity.Jobs;
import com.example.carservice.entity.Role;
import com.example.carservice.entity.User;
import com.example.carservice.repository.JobsRepository;
import com.example.carservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getUsername().equals("admin")) {
            user.setRoles(Set.of(Role.ADMIN));
        } else {
            user.setRoles(Set.of(Role.USER));
        }
        userRepository.save(user);

    }
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            return byUsername.get();
        }
        throw new UsernameNotFoundException("");
    }
}

