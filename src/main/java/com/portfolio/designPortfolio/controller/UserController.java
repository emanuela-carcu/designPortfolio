package com.portfolio.designPortfolio.controller;

import com.portfolio.designPortfolio.model.User;
import com.portfolio.designPortfolio.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @PostMapping("create")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable long id, @RequestBody User userDetails) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        updateUser.setFirstName(userDetails.getFirstName());
        updateUser.setLastName(userDetails.getLastName());
        updateUser.setEmail(userDetails.getEmail());
        updateUser.setPassword(userDetails.getPassword());
        updateUser.setDescription(userDetails.getDescription());
        updateUser.setSkills(userDetails.getSkills());
        updateUser.setExperience(userDetails.getExperience());

        return userRepository.save(updateUser);
    }

    @GetMapping()
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }
}



