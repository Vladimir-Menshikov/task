package com.example.demo.services;

import com.example.demo.models.Role;
import com.example.demo.models.Status;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public User register(User user) {
        Role roleUser = roleRepository.findByName("USER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("User successfully registered");

        return registeredUser;
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
}
