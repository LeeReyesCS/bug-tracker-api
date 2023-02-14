package com.adacapstone.bug.tracker.api.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Register new account or authenticate existing user
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
    @Autowired
    private final AuthenticationService service;


    //endpoint to register new user
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequest request) { // param holds email, pass, etc.
        return ResponseEntity.ok(service.register(request));
    }
    //endpoint to authenticate user
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody AuthenticationRequest request) { // param holds email, pass, etc.
        return ResponseEntity.ok(service.authenticate(request));
    }


}
