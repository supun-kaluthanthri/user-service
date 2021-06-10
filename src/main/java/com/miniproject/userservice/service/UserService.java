package com.miniproject.userservice.service;

import com.miniproject.userservice.model.User;
import com.miniproject.userservice.model.UserCreate;

public interface UserService {

    User createUser(UserCreate user) throws Exception;

    User getUserById(Integer userId) throws Exception;

    User getUserByEmail(String email) throws Exception;

    User getUserByUsername(String username) throws Exception;
}
