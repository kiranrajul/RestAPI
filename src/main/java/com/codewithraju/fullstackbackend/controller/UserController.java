package com.codewithraju.fullstackbackend.controller;

import com.codewithraju.fullstackbackend.exception.UserNotFoundException;
import com.codewithraju.fullstackbackend.model.User;
import com.codewithraju.fullstackbackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    public UserRepo userRepo;

    @PostMapping("/user")
    User newUser(@RequestBody User newuser) {
        return userRepo.save(newuser);
    }

    @GetMapping("/users")
        List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/user/{id}")
    User getUsersById(@PathVariable Long id) {
        return userRepo.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }
    @PutMapping("/user/{id}")
        User updateUser(@RequestBody User newUser,@PathVariable Long id) {
        return userRepo.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepo.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {
        if(!userRepo.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepo.deleteById(id);
        return "User with id" +id+ "has been deleted";
    }
}
