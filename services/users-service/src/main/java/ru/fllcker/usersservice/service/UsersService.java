package ru.fllcker.usersservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.usersservice.models.User;
import ru.fllcker.usersservice.repositories.IUsersRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {
    private final IUsersRepository usersRepository;

    public User create(User user) {
        if (usersRepository.existsByEmail(user.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already exists!");

        return usersRepository.save(user);
    }

    public User findById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

    public User findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }
}
