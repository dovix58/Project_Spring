package com.example.springproject.services.impl;

import com.example.springproject.mappers.Requests.UserRequestMapper;
import com.example.springproject.mappers.Responses.UserResponseMapper;
import com.example.springproject.models.DTOs.Request.UserRequestDTO;
import com.example.springproject.models.DTOs.Response.UserResponseDTO;
import com.example.springproject.models.User;
import com.example.springproject.repositories.UserRepo;
import com.example.springproject.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final UserRequestMapper userRequestMapper;

    private final UserResponseMapper userResponseMapper;


    public UserServiceImpl(UserRepo userRepo, UserRequestMapper userRequestMapper, UserResponseMapper userResponseMapper) {
        this.userRepo = userRepo;
        this.userRequestMapper = userRequestMapper;
        this.userResponseMapper = userResponseMapper;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        User user = userRequestMapper.userDTOToUser(userRequestDTO);
        user.setDateCreated(LocalDateTime.now(ZoneOffset.UTC));
        return userResponseMapper.userToResponseDTO(userRepo.save(user));
    }


    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return userRepo
                .findAll()
                .stream()
                .map(userResponseMapper::userToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponseDTO> updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        User requestUser = userRequestMapper.userDTOToUser(userRequestDTO);
            user.setUsername(requestUser.getUsername());
            userRepo.save(user);
            return Optional.of(userResponseMapper.userToResponseDTO(user));

    }
}
