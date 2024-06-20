package com.swiftrecruit.usermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDto {
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty
    private String password;

}
