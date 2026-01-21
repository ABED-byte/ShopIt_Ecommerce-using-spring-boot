package com.AbedProjects.ShopIt.User.Authentication;


import com.AbedProjects.ShopIt.Dtos.LoginRequestDto;
import com.AbedProjects.ShopIt.Dtos.LoginResponseDto;
import com.AbedProjects.ShopIt.Dtos.RegisterRequestDto;
import com.AbedProjects.ShopIt.Dtos.RegisterResponseDto;
import com.AbedProjects.ShopIt.User.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {

        return ResponseEntity.ok(authService.register(registerRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }


}
