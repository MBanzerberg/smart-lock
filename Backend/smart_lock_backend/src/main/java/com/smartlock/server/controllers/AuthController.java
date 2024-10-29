package com.smartlock.server.controllers;

import com.smartlock.server.entities.LoginMessage;
import com.smartlock.server.entities.LoginResponse;
import com.smartlock.server.repositories.UserRepository;
import com.smartlock.server.services.AuthenticationService;
import com.smartlock.server.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public AuthController(JwtService jwtService, AuthenticationService authenticationService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginMessage loginMessage) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(loginMessage.getPassword());
        System.out.println(encodedPassword);

        LoginMessage authenticatedUser = authenticationService.authenticate(loginMessage);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setUserID(userRepository.findUserIdByUsername(loginMessage.getUsername()));

        System.out.println(loginResponse.getToken());

        return ResponseEntity.ok(loginResponse);
    }

}
