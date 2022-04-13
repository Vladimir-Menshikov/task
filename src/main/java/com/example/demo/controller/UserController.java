package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("account")
    public ResponseEntity<UserDto> account(Authentication authentication){
        User user = userService.findByUsername(authentication.getName());

        if(user == null){
            throw new UserNotFoundException();
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}