package com.example.demokroger.demokroger.controller;
import com.example.demokroger.demokroger.error.UserNotFoundException;
import com.example.demokroger.demokroger.model.User;
import com.example.demokroger.demokroger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }


    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) throws UserNotFoundException {
        return userService.getUserById(id);
    }
}
