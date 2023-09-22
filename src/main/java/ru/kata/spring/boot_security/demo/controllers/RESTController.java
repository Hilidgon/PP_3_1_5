package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception_handler.NoSuchUserException;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.allUsers();
    }

    @GetMapping("/roles")
    public List<Role> showAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {
        User user = userService.show(id);

        if (user == null) {
            throw new NoSuchUserException("There is no user with ID = " + id + " in Database");
        }

        return user;
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.add(user);

        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.edit(user);

        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id) {
        User user = userService.show(id);

        if (user == null) {
            throw new NoSuchUserException("There is no user with ID = " + id + " in Database.");
        }

        userService.delete(id);

        return "User with ID = " + id + " was deleted.";
    }

    @GetMapping(value = "/user")
    public User getUser(Principal principal) {
        return userService.getUserByUsername(principal.getName());
    }
}
