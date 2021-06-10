package com.miniproject.userservice.utils;


import com.miniproject.userservice.dto.Role;
import com.miniproject.userservice.dto.Users;
import com.miniproject.userservice.exception.NotFoundException;
import com.miniproject.userservice.model.UserCreate;
import com.miniproject.userservice.repository.RoleRepository;
import com.miniproject.userservice.repository.UsersDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDtoConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDtoConverter.class);

    @Bean
    PasswordEncoder getPasswordEncoder(){

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return new BCryptPasswordEncoder();
    }

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsersDetailRepository usersDetailRepository;

    public Users userCreateRequestToDto(final UserCreate user) throws Exception{
        Users repoUser = new Users();
        List<Role> roles =  new ArrayList<>();
        LOGGER.info("before getting id");
        Integer id = usersDetailRepository.getNextSeriesId();
        LOGGER.info("id: "+id);
        repoUser.setId(id);
        repoUser.setUsername(user.getEmail());
        repoUser.setEmail(user.getEmail());
        repoUser.setPassword(passwordEncoder.encode(user.getPassword()));
        LOGGER.info("search id");
        //getting roles for role ids in the list
        Optional<Role> role = roleRepository.findById(user.getRoleId());
        LOGGER.info("before role id");
        roles.add(role.get());
        LOGGER.info("after role id");
        repoUser.setRoles(roles);
        repoUser.setEnabled(true);
        repoUser.setAccountNonExpired(true);
        repoUser.setAccountNonLocked(true);
        repoUser.setCredentialsNonExpired(true);

        return repoUser;

    }




}
