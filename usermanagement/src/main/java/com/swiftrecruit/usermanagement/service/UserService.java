package com.swiftrecruit.usermanagement.service;

import com.swiftrecruit.usermanagement.entity.User;
import com.swiftrecruit.usermanagement.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}