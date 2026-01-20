package com.AbedProjects.ShopIt.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {


@Autowired
private AuthService authService;

@Autowired
private UserRepository userRepository;

   @PostMapping("/login")
   ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto)
   {
       return ResponseEntity.ok(authService.login(loginRequestDto));
   }


    @PostMapping("/signup")
    ResponseEntity<signResponseDto> sign (@RequestBody SignRequestDto signRequestDto) {

        return ResponseEntity.ok(authService.signup(signRequestDto));
    }

    @GetMapping("/getUsers")
    List<UserResponseDto> getUsers() {

       List<User> users = userRepository.findAll();

       return users.stream().map(user-> new UserResponseDto(user.getId(),user.getUsername())).toList();
    }

}
