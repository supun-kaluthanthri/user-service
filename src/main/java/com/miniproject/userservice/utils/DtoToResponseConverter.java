package com.miniproject.userservice.utils;

import com.miniproject.userservice.dto.Users;
import com.miniproject.userservice.model.Role;
import com.miniproject.userservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoToResponseConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(DtoToResponseConverter.class);
    /**
     * Convert User dto to User model object.
     */
    public User userDtoToUserResponse(Users repoUser){
        final User user =  new User();
        List<Role> roles = new ArrayList<>();
        user.setId(repoUser.getId());
        user.setEmail(repoUser.getEmail());
        user.setUsername(repoUser.getUsername());
        user.setPassword(repoUser.getPassword());
        LOGGER.info("test"+ repoUser.getRoles());
        user.setEnabled(repoUser.isEnabled());
        user.setAccountNonExpired(repoUser.isAccountNonExpired());
        user.setAccountNonLocked(repoUser.isAccountNonLocked());
        user.setCredentialsNonExpired(repoUser.isCredentialsNonExpired());

        return user;
    }
}
