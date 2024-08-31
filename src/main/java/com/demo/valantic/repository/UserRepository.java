package com.demo.valantic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.valantic.entity.User;
import com.demo.valantic.model.UserDto;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
   // void saveUser(UserDto userDto);
}
