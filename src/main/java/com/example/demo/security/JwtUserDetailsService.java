package com.example.demo.security;

import com.example.demo.models.User;
import com.example.demo.security.jwt.JwtUser;
import com.example.demo.security.jwt.JwtUserFactory;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("User with username {} successfully loaded", username);
        return jwtUser;
    }
}
