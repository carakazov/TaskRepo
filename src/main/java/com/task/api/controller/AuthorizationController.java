package com.task.api.controller;

import com.task.api.dto.AuthorizationUserDTO;
import com.task.api.dto.InputUserDTO;
import com.task.api.dto.UserDTO;
import com.task.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {
    private final UserService userService;
    @Autowired
    public AuthorizationController(UserService userService){
        this.userService = userService;
    }

    //Добавить пользователя.
    @PostMapping(value = "/users")
    public ResponseEntity<String> addUser(@RequestBody InputUserDTO newUser){
        String result = userService.add(newUser);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    //Осуществить авторизацию.
    @RequestMapping(value = "/users/authorize")
    public ResponseEntity<UserDTO> authorize(@RequestBody AuthorizationUserDTO authorizationUser){
        UserDTO userDTO = userService.authorize(authorizationUser);
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }
}
