package com.AbedProjects.ShopIt.Dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {


    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = " password is required")
    private String password;

    @NotBlank(message = "email is required")
    private String email;

}
