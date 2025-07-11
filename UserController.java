package com.example.CrudProject.Crud.Project;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList<>();
    private int userIdCounter = 1;

    // Create a user
    @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam String email) {
        User user = new User();
        user.setId(userIdCounter++);
        user.setName(name);
        user.setEmail(email);
        users.add(user);
        return "User added: " + user.getName();
    }

    // Get all users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return users;
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Update a user by ID
    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable int id, @RequestParam String name, @RequestParam String email) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(name);
                user.setEmail(email);
                return "User updated: " + user.getName();
            }
        }
        return "User not found";
    }

    // Delete a user by ID
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return "User deleted: " + user.getName();
            }
        }
        return "User not found";
    }
}
