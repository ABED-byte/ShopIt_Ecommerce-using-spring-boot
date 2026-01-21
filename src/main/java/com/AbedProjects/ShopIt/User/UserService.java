package com.AbedProjects.ShopIt.User;

import com.AbedProjects.ShopIt.Dtos.LoginRequestDto;
import com.AbedProjects.ShopIt.Dtos.LoginResponseDto;
import com.AbedProjects.ShopIt.Dtos.RegisterRequestDto;
import com.AbedProjects.ShopIt.Dtos.RegisterResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    UserRepo userRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }



}
