package com.AbedProjects.ShopIt.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import thirdProject.EntityClasses.Patient;
import thirdProject.Repositories.UserRepo;
import thirdProject.config.RoleType;

import java.util.Set;




@Service
public class AuthService {
    @Autowired
    private AuthUtil authUtil;


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

       String token = authUtil.generateJwtToken(user);
       return new LoginResponseDto(token, user.getId());

    }

     public signResponseDto signup(SignRequestDto signRequestDto) {
        User user = userRepository.findByUsername(signRequestDto.getUsername()).orElse(null);

        if(user != null)  throw new IllegalArgumentException("Username already exists");

         User user1 = userRepository.save(User.builder().
                username(signRequestDto.getUsername()).
                password(passwordEncoder.encode(signRequestDto.getPassword())).
//                roles(Set.of(RoleType.PATIENT)).
                build());

         patient = Patient.builder()
                .patientName(signRequestDto.getName()).
                user(user1).
                build();

        userRepo.save(patient);


        return new signResponseDto(user1.getId(),user1.getUsername());


    }
}
