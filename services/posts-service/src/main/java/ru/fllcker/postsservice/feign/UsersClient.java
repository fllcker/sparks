package ru.fllcker.postsservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.fllcker.postsservice.vo.User;

@FeignClient("USERS-SERVICE")
public interface UsersClient {
    @GetMapping("users/id/{id}")
    User findById(@PathVariable Long id);

    @GetMapping("users/email/{email}")
    User findByEmail(@PathVariable String email);
}
