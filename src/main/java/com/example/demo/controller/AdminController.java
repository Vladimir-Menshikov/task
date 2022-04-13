package com.example.demo.controller;

import com.example.demo.dto.AdminUserDto;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "users/{id}")
    public AdminUserDto getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new UserNotFoundException();
        }

        return AdminUserDto.fromUser(user);
    }
}
