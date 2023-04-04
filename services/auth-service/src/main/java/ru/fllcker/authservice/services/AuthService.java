package ru.fllcker.authservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.authservice.feign.UsersClient;
import ru.fllcker.authservice.models.Token;
import ru.fllcker.authservice.utils.JwtAuthentication;
import ru.fllcker.authservice.vo.JwtRequest;
import ru.fllcker.authservice.vo.JwtResponse;
import ru.fllcker.authservice.vo.SignUpDto;
import ru.fllcker.authservice.vo.User;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder encoder;
    private final UsersClient usersClient;
    private final JwtProvider jwtProvider;
    private final TokensService tokensService;

    public JwtResponse login(JwtRequest jwtRequest) {
        User user = usersClient.findByEmail(jwtRequest.getEmail());

        if (!encoder.matches(jwtRequest.getPassword(), user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong data");

        String accessToken = jwtProvider.generateToken(user, false);
        String refreshToken = jwtProvider.generateToken(user, true);
        tokensService.create(refreshToken, user.getId());

        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse signup(SignUpDto dto) {
        User user = new User(dto.getEmail(), encoder.encode(dto.getPassword()));
        user = usersClient.create(user);

        String accessToken = jwtProvider.generateToken(user, false);
        String refreshToken = jwtProvider.generateToken(user, true);
        tokensService.create(refreshToken, user.getId());

        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse getTokensByRefresh(String refreshToken, boolean refresh) {
        String subject = jwtProvider.getRefreshClaims(refreshToken)
                .getSubject();

        if (!jwtProvider.validateRefreshToken(refreshToken))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        Token token = tokensService.findByValue(refreshToken);

        if (!token.getValue().equals(refreshToken))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        User user = usersClient.findByEmail(subject);

        String accessToken = jwtProvider.generateToken(user, false);
        String newRefreshToken = null;

        if (refresh) {
            newRefreshToken = jwtProvider.generateToken(user, true);
            tokensService.create(newRefreshToken, user.getId());
        }

        return new JwtResponse(accessToken, newRefreshToken);
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
