package ru.fllcker.authservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.fllcker.authservice.vo.User;

@FeignClient("USERS-SERVICE")
public interface UsersClient {
    @PostMapping
    User create(@RequestBody User user);

    @GetMapping("id/{id}")
    User findById(@PathVariable Long id);

    @GetMapping("email/{email}")
    User findByEmail(@PathVariable String email);
}
