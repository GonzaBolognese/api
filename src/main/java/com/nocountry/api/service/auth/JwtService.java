package com.nocountry.api.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class JwtService {

    public String generateToken(UserDetails user) {
        String jwt = null;

        return jwt;
    }



}
