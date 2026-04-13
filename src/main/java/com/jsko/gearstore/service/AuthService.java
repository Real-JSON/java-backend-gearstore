package com.jsko.gearstore.service;

import com.jsko.gearstore.dto.request.RegisterRequest;
import com.jsko.gearstore.dto.response.AuthResponse;
import com.jsko.gearstore.model.Role;
import com.jsko.gearstore.model.User;
import com.jsko.gearstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered!");
        }

        // Build the new User object
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Hash the password
                .role(Role.CUSTOMER) // Default role
                .build();

        // Save to database
        userRepository.save(user);

        // Return a response
        return AuthResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .role(user.getRole().name())
                .token("JWT_TOKEN_COMING_SOON") // Placeholder for JWT token generation
                .build();
    }
}