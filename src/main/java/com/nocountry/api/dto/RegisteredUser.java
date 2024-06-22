package com.nocountry.api.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class RegisteredUser implements Serializable {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String role;

    private String jwt;

}
