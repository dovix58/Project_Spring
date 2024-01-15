package com.example.springproject.services.impl;

import com.example.springproject.models.User;
import com.example.springproject.repositories.UserRepository;
import com.example.springproject.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
