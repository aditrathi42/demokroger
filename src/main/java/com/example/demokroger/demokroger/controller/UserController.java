package com.example.demokroger.demokroger.controller;
import com.example.demokroger.demokroger.bean.LoginRequest;
import com.example.demokroger.demokroger.config.AuthenticationResponse;
import com.example.demokroger.demokroger.error.UserNotFoundException;
import com.example.demokroger.demokroger.model.User;
import com.example.demokroger.demokroger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
