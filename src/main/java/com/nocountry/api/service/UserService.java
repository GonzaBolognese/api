package com.nocountry.api.service;

import com.nocountry.api.dto.SaveUser;
import com.nocountry.api.persistence.entity.User;

public interface UserService {

    User createOneCustomer(SaveUser newUser);

}
