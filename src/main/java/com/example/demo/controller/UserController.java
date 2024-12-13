package com.example.demo.controller;

import com.example.demo.dto.TodoDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Todo;
import com.example.demo.model.User;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            return userDto;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user = userRepository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }
}

