package com.AbedProjects.ShopIt;

import com.AbedProjects.ShopIt.User.RoleType;
import com.AbedProjects.ShopIt.User.UserEntity;
import com.AbedProjects.ShopIt.User.UserRepo;
import com.AbedProjects.ShopIt.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class MockitoTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void loadByUsername() {
        when(userRepo.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(
                        UserEntity.builder()
                                .username("abedTest")
                                .password("342342")
                                .email("abedshaikh966@example.com")
                                .roles(Set.of(RoleType.CUSTOMER))
                                .build()
                ));

        UserDetails result = userService.loadUserByUsername("abedTest");
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("abedTest");
        assertThat(result.getAuthorities()).hasSize(1);
    }
}
