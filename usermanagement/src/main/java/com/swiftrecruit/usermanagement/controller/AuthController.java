package com.swiftrecruit.usermanagement.controller;

import jakarta.validation.Valid;
import com.swiftrecruit.usermanagement.dto.UserDto;
import com.swiftrecruit.usermanagement.entity.User;
import com.swiftrecruit.usermanagement.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public ResponseEntity<Object> home() {
        return new ResponseEntity<Object>("Welcome to the index page!", HttpStatus.OK);
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register")
    public ResponseEntity<Object> registration(@Valid @RequestBody UserDto userDto,
            BindingResult result,
            Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return new ResponseEntity<Object>("Registration Failed", HttpStatus.BAD_REQUEST);
        }

        userService.saveUser(userDto);
        return new ResponseEntity<Object>(userDto, HttpStatus.OK);
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}