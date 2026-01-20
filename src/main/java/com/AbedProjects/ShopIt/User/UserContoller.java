package com.AbedProjects.ShopIt.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserContoller {

    @Autowired
    private UserService userService;




}
