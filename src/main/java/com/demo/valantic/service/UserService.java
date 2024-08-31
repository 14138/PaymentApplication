package com.demo.valantic.service;

import com.demo.valantic.model.UserDto;
import com.demo.valantic.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto>findAllUsers();
}
