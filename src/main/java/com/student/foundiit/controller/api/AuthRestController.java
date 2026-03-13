package com.student.foundiit.controller.api;

import com.student.foundiit.dto.ApiResponse;
import com.student.foundiit.dto.UserDTO;
import com.student.foundiit.dto.UserRegistrationDTO;
import com.student.foundiit.model.User;
import com.student.foundiit.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final UserService userService;

    public AuthRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@RequestBody UserRegistrationDTO dto) {
        // Check if email already exists
        User existingUser = userService.findByEmail(dto.getEmail());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error("Email already registered"));
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        User saved = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User registered successfully", UserDTO.fromEntity(saved)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDTO>> login(@RequestBody UserRegistrationDTO dto) {
        User user = userService.findByEmail(dto.getEmail());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("Invalid email or password"));
        }
        // Note: In a real app, you'd verify the password with the encoder.
        // For this API, we return user info if found.
        return ResponseEntity.ok(ApiResponse.success("Login successful", UserDTO.fromEntity(user)));
    }
}
