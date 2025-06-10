package org.saima.LMS.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.saima.LMS.config.JwtTokenProvider;
import org.saima.LMS.dto.LoginRequest;
import org.saima.LMS.dto.RegisterRequest;
import org.saima.LMS.dto.UserResponse;
import org.saima.LMS.model.CustomUserDetails;
import org.saima.LMS.model.User;
import org.saima.LMS.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterRequest registerRequest
    ) {
        try {
            User user = new User(
                    registerRequest.email(),
                    registerRequest.password(),
                    registerRequest.role(),
                    registerRequest.name(),
                    registerRequest.fatherName(),
                    registerRequest.motherName(),
                    registerRequest.phone(),
                    registerRequest.address(),
                    registerRequest.avatarUrl(),
                    registerRequest.bio()
                    
            );

            User savedUser = userService.createUser(user);

            // Create DTO to return (exclude sensitive info)
            UserResponse userResponse = new UserResponse();
            userResponse.setId(savedUser.getId());
            userResponse.setEmail(savedUser.getEmail());
            userResponse.setRole(savedUser.getRole());
            userResponse.setName(savedUser.getName());
            userResponse.setFatherName(savedUser.getFatherName());
            userResponse.setMotherName(savedUser.getMotherName());
            userResponse.setPhone(savedUser.getPhone());

            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(HttpServletRequest request,
                                              HttpServletResponse response,
                                              @Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.createToken(authentication);

            // Get user details
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userDetails.user();

            // Create response with token and user info
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("access_token", jwt);
            responseData.put("role", user.getRole());
            responseData.put("id", user.getId());
            

            // Add user information
//            Map<String, Object> userData = new HashMap<>();
//            userData.put("id", user.getId());
//            userData.put("email", user.getEmail());
//            userData.put("role", user.getRole());
//            userData.put("firstName", user.getFatherName());
//            userData.put("lastName", user.getMotherName());
//
//            responseData.put("user", userData);

            return ResponseEntity.ok(responseData);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }

    // This endpoint can be called from Angular to check if a token is valid
    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        String jwt = getJwtFromRequest(request);

        if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
            String username = jwtTokenProvider.getUsernameFromToken(jwt);
            UserDetails userDetails = userService.loadUserByUsername(username);

            // Return user information
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
            User user = customUserDetails.user();

            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setEmail(user.getEmail());
            userResponse.setRole(user.getRole());
            userResponse.setName(user.getName());
            userResponse.setFatherName(user.getFatherName());
            userResponse.setMotherName(user.getMotherName());

            return ResponseEntity.ok(userResponse);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
    }

    // Helper method to extract JWT from request
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}