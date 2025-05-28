package com.user.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.user.demo.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}