package com.miniproject.userservice.controller;


import com.miniproject.userservice.model.User;
import com.miniproject.userservice.model.UserCreate;
import com.miniproject.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RequestMapping("/users")
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(final @RequestBody UserCreate user) throws Exception {
        LOGGER.trace("Create user api invoked");
        final User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<Object> test() throws Exception {
        LOGGER.trace("Create user api invoked");
        final String testStr = "yes it is working";
        return new ResponseEntity<>(testStr, HttpStatus.OK);
    }

}
