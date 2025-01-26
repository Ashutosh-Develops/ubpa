package com.github.ashutosh.ubpa.controller;

import com.github.ashutosh.ubpa.dto.request.LoginRequest;
import com.github.ashutosh.ubpa.dto.request.UserRequestBody;
import com.github.ashutosh.ubpa.dto.response.AuthenticationResponse;
import com.github.ashutosh.ubpa.dto.response.UserResponseBody;
import com.github.ashutosh.ubpa.entity.User;
import com.github.ashutosh.ubpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/auth/register")
    public ResponseEntity<UserResponseBody> userRegistration(@RequestBody UserRequestBody userRequestBody){
         User user=userService.registerUser(userRequestBody);
         UserResponseBody userResponseBody=UserResponseBody.generateResponseBodyFromUser(user);
         return new ResponseEntity(userResponseBody, HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> userLogIn(@RequestBody LoginRequest loginRequest){

       AuthenticationResponse response=userService.loginUser(loginRequest);
       return new ResponseEntity<AuthenticationResponse>(response,HttpStatus.OK);
    }
}
