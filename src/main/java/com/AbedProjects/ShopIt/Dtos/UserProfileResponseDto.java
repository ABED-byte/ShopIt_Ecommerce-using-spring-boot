package com.AbedProjects.ShopIt.Dtos;

import com.AbedProjects.ShopIt.User.RoleType;
import com.AbedProjects.ShopIt.User.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponseDto {
    Long id;

    String username;

    String email;

    Set<RoleType> roles;

    public UserProfileResponseDto(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}
