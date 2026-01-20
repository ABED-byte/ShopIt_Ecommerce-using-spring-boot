
    package com.AbedProjects.ShopIt.Security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class SignRequestDto {
        String username;
        String password;
        String name;
    }


