package com.example.springproject.repositories;

import com.example.springproject.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
