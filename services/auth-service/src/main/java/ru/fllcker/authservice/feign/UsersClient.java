package ru.fllcker.authservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.fllcker.authservice.vo.User;

@FeignClient("USERS-SERVICE")
public interface UsersClient {
    @PostMapping("users")
    User create(@RequestBody User user);

    @GetMapping("users/id/{id}")
    User findById(@PathVariable Long id);

    @GetMapping("users/email/{email}")
    User findByEmail(@PathVariable String email);
}
