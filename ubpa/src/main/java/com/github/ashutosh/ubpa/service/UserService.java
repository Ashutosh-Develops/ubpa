package com.github.ashutosh.ubpa.service;

import com.github.ashutosh.ubpa.dto.request.LoginRequest;
import com.github.ashutosh.ubpa.dto.request.UserRequestBody;
import com.github.ashutosh.ubpa.dto.response.AuthenticationResponse;
import com.github.ashutosh.ubpa.entity.User;
import com.github.ashutosh.ubpa.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserRequestBody userRequestBody){

        User user = getUserFromRequestBody(userRequestBody);
        User savedUser=userRepository.save(user);

        // Generate token and send to the user
        return savedUser;
    }

    private User getUserFromRequestBody(UserRequestBody userRequestBody){

        String firstName=userRequestBody.getFirstName();
        String lastName= userRequestBody.getLastName();
        String email = userRequestBody.getEmail();
        String password = userRequestBody.getPassword();

        User user=new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return user;
    }

    public AuthenticationResponse loginUser(LoginRequest loginRequest){

        if(loginRequest.getEmail()==null||loginRequest.getEmail().isEmpty()){
            throw new RuntimeException("Invalid email");
        }

        if(loginRequest.getPassword()==null||loginRequest.getPassword().isEmpty()){
            throw new RuntimeException("Invalid password");
        }

        String username=loginRequest.getEmail();
        String password=loginRequest.getPassword();


        // Get the authentication instance for the username and password

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        
        AuthenticationResponse response=new AuthenticationResponse();
        String message = username+" authenticated successfully !";
        response.setMessage(message);
        return response;

    }


}
