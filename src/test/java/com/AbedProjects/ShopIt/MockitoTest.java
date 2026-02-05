package com.AbedProjects.ShopIt;

import com.AbedProjects.ShopIt.Product.ProductRepo;
import com.AbedProjects.ShopIt.User.UserEntity;
import com.AbedProjects.ShopIt.User.UserRepo;
import com.AbedProjects.ShopIt.User.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.Mockito.when;
// removed jpa test component,which will not call unnecessary components
public class MockitoTest {



    @InjectMocks               //only neccessary mocks
    private UserService userService;


    @Mock   //gives mock value
    private UserRepo userRepo;


    @BeforeEach //to initialize userRepo
    void init()
        {
        MockitoAnnotations.initMocks(this);
        }

    @Test
    void loadByUsername(){
        when(userRepo.findByUsername(ArgumentMatchers.anyString())).thenReturn(
                Optional.ofNullable(UserEntity.builder().username("abedTest").password("342342").email("abedshaikh966").build()));

        assertThat(userService.loadUserByUsername("abedTest")).isNotNull();
    }
}
