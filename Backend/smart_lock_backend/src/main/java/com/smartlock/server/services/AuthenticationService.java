package com.smartlock.server.services;

import com.smartlock.server.entities.LoginMessage;
import com.smartlock.server.entities.LoginMessageProjection;
import com.smartlock.server.entities.User;
import com.smartlock.server.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public LoginMessage authenticate(LoginMessage input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        LoginMessageProjection projection = userRepository
                .findUserLoginMessage(input.getUsername())
                .orElseThrow();

        LoginMessage authenticatedUser = new LoginMessage();
        authenticatedUser.setUsername(projection.getUsername());
        authenticatedUser.setPassword(projection.getPassword());

        return authenticatedUser;
    }
}
