package com.AbedProjects.ShopIt.User.Authentication;

import com.AbedProjects.ShopIt.Dtos.LoginRequestDto;
import com.AbedProjects.ShopIt.Dtos.LoginResponseDto;
import com.AbedProjects.ShopIt.Dtos.RegisterRequestDto;
import com.AbedProjects.ShopIt.Dtos.RegisterResponseDto;
import com.AbedProjects.ShopIt.User.RoleType;
import com.AbedProjects.ShopIt.User.UserEntity;
import com.AbedProjects.ShopIt.User.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUtil authUtil;

    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        UserEntity user = userRepo.findByUsername(registerRequestDto.getUsername())
                .orElse(null);

        if(user != null)  throw new IllegalArgumentException("Username already exists");
        log.info("Register email received = {}", registerRequestDto.getEmail());


        UserEntity user1 = userRepo.save(UserEntity.builder().
                username(registerRequestDto.getUsername())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .email(registerRequestDto.getEmail())
                .roles(Set.of(RoleType.CUSTOMER))
                .build());

        return new RegisterResponseDto(user1);

    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(), loginRequestDto.getPassword()
        ));

        UserEntity user = (UserEntity) authentication.getPrincipal();

        String token = authUtil.generateJwtToken(user);

        return new LoginResponseDto(token,user.getId());


    }
}
