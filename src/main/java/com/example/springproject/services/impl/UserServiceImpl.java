package com.example.springproject.services.impl;

import com.example.springproject.mappers.Requests.UserMapper;
import com.example.springproject.mappers.Responses.UserResponseMapper;
import com.example.springproject.models.DTOs.Request.UserRequestDTO;
import com.example.springproject.models.DTOs.Response.UserResponseDTO;
import com.example.springproject.models.User;
import com.example.springproject.repositories.UserRepo;
import com.example.springproject.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        User user = UserMapper.INSTANCE.userDTOToUser(userRequestDTO);
        return UserResponseMapper.INSTANCE.userToResponseDTO(userRepo.save(user));
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
                .map(UserResponseMapper.INSTANCE::userToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponseDTO> updateUser(Long id, UserRequestDTO userRequestDTO) {
        Optional<User> existingUser = userRepo.findById(id);
        if(existingUser.isPresent()){
            User userToUpdate = existingUser.get();
            User requestUser = UserMapper.INSTANCE.userDTOToUser(userRequestDTO);
            userToUpdate.setUsername(requestUser.getUsername());
            userRepo.save(userToUpdate);
            return Optional.of(UserResponseMapper.INSTANCE.userToResponseDTO(userToUpdate));
        }
        return Optional.empty();
    }
}
