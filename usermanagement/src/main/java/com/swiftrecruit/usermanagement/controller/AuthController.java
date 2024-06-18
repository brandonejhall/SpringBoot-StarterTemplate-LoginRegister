package com.swiftrecruit.usermanagement.controller;

import jakarta.validation.Valid;

import com.swiftrecruit.usermanagement.config.ApiResponse;
import com.swiftrecruit.usermanagement.dto.UserDto;
import com.swiftrecruit.usermanagement.entity.User;
import com.swiftrecruit.usermanagement.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    /*
     * @PostMapping(value = "/register")
     * public ResponseEntity<Object> registration(@Valid @RequestBody UserDto
     * userDto,
     * BindingResult result,
     * Model model) {
     * System.out.println("Received UserDto: " + userDto);
     * 
     * User existingUser = userService.findUserByEmail(userDto.getEmail());
     * 
     * if (existingUser != null && existingUser.getEmail() != null &&
     * !existingUser.getEmail().isEmpty()) {
     * result.rejectValue("email", null,
     * "There is already an account registered with the same email");
     * }
     * 
     * if (result.hasErrors()) {
     * model.addAttribute("user", userDto);
     * return new ResponseEntity<Object>("Registration Failed",
     * HttpStatus.BAD_REQUEST);
     * }
     * 
     * userService.saveUser(userDto);
     * return new ResponseEntity<Object>(userDto, HttpStatus.OK);
     * }
     */

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}