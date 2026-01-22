package com.AbedProjects.ShopIt.User;


import com.AbedProjects.ShopIt.Dtos.UserProfileResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/viewme")
    public UserProfileResponseDto SeeMyProfile (@AuthenticationPrincipal UserEntity user)
    {

        return new UserProfileResponseDto(user);
    }


}
