package com.miniproject.userservice.service.impl;

import com.miniproject.userservice.dto.Users;
import com.miniproject.userservice.exception.NotFoundException;
import com.miniproject.userservice.model.User;
import com.miniproject.userservice.model.UserCreate;
import com.miniproject.userservice.repository.UsersDetailRepository;
import com.miniproject.userservice.service.UserService;
import com.miniproject.userservice.utils.DtoToResponseConverter;
import com.miniproject.userservice.utils.RequestValidator;
import com.miniproject.userservice.utils.UserDtoConverter;
import com.miniproject.userservice.utils.ValidationConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserService {
    @Autowired
    UsersDetailRepository userDetailRepository;

    @Autowired
    RequestValidator requestValidator;

    @Autowired
    DtoToResponseConverter dtoToResponseConverter;

    @Autowired
    UserDtoConverter userDtoConverter;


    @Override
    public User createUser(UserCreate user) throws Exception {
        requestValidator.validateUserCreateRequest(user);

        final Users createdUser = userDetailRepository.save(userDtoConverter.userCreateRequestToDto(user));

        return dtoToResponseConverter.userDtoToUserResponse(createdUser);


    }

    @Override
    public User getUserById(Integer userId) throws Exception {
        Optional<Users> user = userDetailRepository.findById(userId);

        if(!user.isPresent()){
            throw new NotFoundException(ValidationConst.USER_NOT_FOUND,ValidationConst.USER_NOT_FOUND.message() +
                    ValidationConst.ATTRIBUTE_ID.message() + userId);
        }

        return dtoToResponseConverter.userDtoToUserResponse(user.get());
    }

    @Override
    public User getUserByEmail(String email) throws Exception {
        Optional<Users> user = userDetailRepository.findByEmail(email);

        requestValidator.isValidEmailAddress(email);

        if(!user.isPresent()){
            throw new NotFoundException(ValidationConst.USER_NOT_FOUND,ValidationConst.USER_NOT_FOUND.message() +
                    ValidationConst.EMAIL_ID.message() + email);
        }

        return dtoToResponseConverter.userDtoToUserResponse(user.get());
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        Optional<Users> user = userDetailRepository.findByUsername(username);

        if(!user.isPresent()){
            throw new NotFoundException(ValidationConst.USER_NOT_FOUND,ValidationConst.USER_NOT_FOUND.message() +
                    ValidationConst.USERNAME_ID.message() + username);
        }

        return dtoToResponseConverter.userDtoToUserResponse(user.get());
    }






}
