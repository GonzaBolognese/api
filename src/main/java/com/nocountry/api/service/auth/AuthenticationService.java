package com.nocountry.api.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nocountry.api.dto.RegisteredUser;
import com.nocountry.api.dto.SaveUser;
import com.nocountry.api.persistence.entity.User;
import com.nocountry.api.service.UserService;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    public RegisteredUser registerOneCustomer(SaveUser newUser) {

        User user = userService.createOneCustomer(newUser);

        RegisteredUser userDto =  new RegisteredUser();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().name());
        
        String jwt = jwtService.generateToken(user);
        userDto.setJwt(jwt);

        return userDto;
    }

}
