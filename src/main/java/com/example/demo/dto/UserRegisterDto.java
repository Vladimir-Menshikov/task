package com.example.demo.dto;

import com.example.demo.exceptions.RegistrationException;
import com.example.demo.models.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class UserRegisterDto {
    private String username;
    private String email;
    private String password;
    private String password2;

    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        return user;
    }
}
