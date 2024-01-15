package com.example.springproject.repositories;

import com.example.springproject.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
