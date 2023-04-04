package ru.fllcker.postsservice.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fllcker.postsservice.security.utils.JwtAuthentication;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}

