package ru.fllcker.usersservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.fllcker.usersservice.models.User;
import ru.fllcker.usersservice.service.UsersService;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UsersController {
    private final UsersService usersService;

    @PostMapping
    public User create(@RequestBody User user) {
        return usersService.create(user);
    }

    @GetMapping("id/{id}")
    public User findById(@PathVariable Long id) {
        return usersService.findById(id);
    }

    @GetMapping("email/{email}")
    public User findByEmail(@PathVariable String email) {
        return usersService.findByEmail(email);
    }
}
