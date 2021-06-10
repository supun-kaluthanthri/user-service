package com.miniproject.userservice.utils;

import com.miniproject.userservice.dto.Users;
import com.miniproject.userservice.exception.AlreadyExistedException;
import com.miniproject.userservice.model.UserCreate;
import com.miniproject.userservice.repository.UsersDetailRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
public class RequestValidator {
    @Autowired
    UsersDetailRepository usersDetailRepository;

    public void validateUserCreateRequest(UserCreate user) throws Exception {

        Optional<Users> repoUserByEmail = usersDetailRepository.findByEmail(user.getEmail());

        if(repoUserByEmail.isPresent()){
            throw new AlreadyExistedException(ValidationConst.USER_EMAIL_ALREADY_EXISTED,ValidationConst.USER_EMAIL_ALREADY_EXISTED.message() +
                    ValidationConst.EMAIL_ID + user.getEmail());
        }

        Optional<Users> repoUserByUsername = usersDetailRepository.findByUsername(user.getEmail());

        if(repoUserByUsername.isPresent()){
            throw new AlreadyExistedException(ValidationConst.USERNAME_ALREADY_EXISTED,ValidationConst.USERNAME_ALREADY_EXISTED.message() +
                    ValidationConst.USERNAME_ID + user.getEmail());
        }

    }

    public boolean isValidEmailAddress(final String email) {
        if (!isNullOrEmpty(email)) {
            return EmailValidator.getInstance().isValid(email);
        }
        return false;
    }

    private boolean isNullOrEmpty(final String checkString) {
        return checkString == null || StringUtils.containsWhitespace(checkString) || StringUtils.isEmpty(checkString);
    }

}
