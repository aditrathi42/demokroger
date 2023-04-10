package com.example.demokroger.demokroger.service;

import com.example.demokroger.demokroger.bean.LoginRequest;
import com.example.demokroger.demokroger.config.AuthenticationResponse;
import com.example.demokroger.demokroger.config.JwtService;
import com.example.demokroger.demokroger.error.UserNotFoundException;
import com.example.demokroger.demokroger.model.Role;
import com.example.demokroger.demokroger.model.User;
import com.example.demokroger.demokroger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public User getUserById(Integer id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Employee Not Found!"));
    }
}
