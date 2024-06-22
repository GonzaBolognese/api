package com.nocountry.api.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SaveUser implements Serializable {

    @Size(min = 4)
    private String name;

    private String username;
    
    private String email;

    @Size(min = 8)
    private String password;

    @Size(min = 8)
    private String repeatedPassword;


}
