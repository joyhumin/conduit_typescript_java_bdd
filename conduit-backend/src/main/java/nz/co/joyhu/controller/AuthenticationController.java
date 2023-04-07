package nz.co.joyhu.controller;

import nz.co.joyhu.api.v1.*;
import nz.co.joyhu.service.JwtTokenProvider;
import nz.co.joyhu.service.JwtUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin // allow cross-origin resource sharing
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final JwtUserDetailsService userDetailsService;

    public AuthenticationController(
            AuthenticationManager authenticationManager,
            JwtTokenProvider tokenProvider,
            JwtUserDetailsService userDetailsService
    ) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> registerNewUser(@RequestBody NewUserRequest newUserRequest) {
        log.info("Request body = {}", newUserRequest);
        final NewUser user = newUserRequest.getUser();
        userDetailsService.save(user);
        final String email = user.getEmail();
        final UserResponse response = userDetailsService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("users/login")
    public ResponseEntity<UserResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        final AuthenticationRequestDetails loginRequest = authenticationRequest.getUser();
        final UserResponse response = userDetailsService.findByEmail(loginRequest.getEmail());
        return ResponseEntity.ok(response);
    }
}
