package com.swiftrecruit.usermanagement.controller;

import jakarta.validation.Valid;

import com.swiftrecruit.usermanagement.config.ApiResponse;
import com.swiftrecruit.usermanagement.dto.LoginDto;
import com.swiftrecruit.usermanagement.dto.UserDto;
import com.swiftrecruit.usermanagement.entity.User;
import com.swiftrecruit.usermanagement.service.UserService;
import com.swiftrecruit.usermanagement.service.impl.CustomUserDetailsService;
import com.swiftrecruit.usermanagement.config.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
public class AuthController {

    private UserService userService;

    @Autowired
    private CustomUserDetailsService custUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public ResponseEntity<Object> home() {
        return new ResponseEntity<Object>("Welcome to the index page!", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserDto userDto) {
        try {
            User existingUser = userService.findUserByEmail(userDto.getEmail());

            if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
                ApiResponse response = new ApiResponse("Email already in use", HttpStatus.CONFLICT.value());
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
            userService.saveUser(userDto);
            ApiResponse response = new ApiResponse("User registered successfully", HttpStatus.CREATED.value());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {

            ApiResponse response = new ApiResponse("Failed to register user: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@Valid @RequestBody LoginDto loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            final UserDetails userDetails = custUserService.loadUserByUsername(loginRequest.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);

            ApiResponse response = new ApiResponse(jwt, HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AuthenticationException e) {
            ApiResponse response = new ApiResponse("Invalid email or password", HttpStatus.UNAUTHORIZED.value());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}