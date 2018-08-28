package com.example.springbootexample.repository;

import com.example.springbootexample.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
