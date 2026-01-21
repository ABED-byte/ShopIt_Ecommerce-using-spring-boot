package com.AbedProjects.ShopIt.Dtos;


import com.AbedProjects.ShopIt.User.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDto {

    Long id;
    String username;

    public RegisterResponseDto(UserEntity userEntity) {
        this.username = userEntity.getUsername();
        this.id = userEntity.getId();
    }


}
