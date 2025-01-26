package com.github.ashutosh.ubpa.service;

import com.github.ashutosh.ubpa.entity.User;
import com.github.ashutosh.ubpa.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String email=username;

        User user=userRepository.findByEmail(email);

        if(user==null){
            throw new RuntimeException("User with email id "+email+" does not exist");
        }


        return org.springframework.security.core.userdetails.User.withUsername(email)
                .password(user.getPassword())
                .build();
    }
}
