package com.example.demo.services;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.exceptions.NonUniqueIsbnException;
import com.example.demo.exceptions.RegistrationException;
import com.example.demo.exceptions.UsernameTakenException;
import com.example.demo.models.Role;
import com.example.demo.models.Status;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserDto register(UserRegisterDto userRegisterDto) {
        if(userRepository.existsByUsername(userRegisterDto.getUsername())) {
            throw new UsernameTakenException();
        }

        checkPasswords(userRegisterDto.getPassword(), userRegisterDto.getPassword2());

        Role roleUser = roleRepository.findByName("USER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser);

        User user = userRegisterDto.toUser();
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        Date now = new Date();
        user.setCreated(now);
        user.setUpdated(now);


        UserDto registeredUserDto = UserDto.fromUser(userRepository.save(user));

        log.info("User successfully registered");

        return registeredUserDto;
    }

    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("{} users found", result.size());
        return result;
    }

    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("User found by username {}", username);
        return result;
    }

    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("No user found by id {}", id);
            return null;
        }

        log.info("User found by id {}", id);
        return result;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("User with id {} successfully deleted", id);
    }

   private void checkPasswords(String password, String password2) {
        if (password == null) {
            throw new RegistrationException("Password cannot be empty");
        }

        if (password2 == null) {
            throw new RegistrationException("Password confirmation cannot be empty");
        }

        if (!password.equals(password2)) {
            throw new RegistrationException("Passwords are different!");
        }
    }
}
