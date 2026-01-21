package com.AbedProjects.ShopIt.Security;

import com.AbedProjects.ShopIt.User.Authentication.AuthUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@Slf4j
public class JwtSecurityContextPipeLine extends OncePerRequestFilter {

    @Autowired
    private AuthUtil authUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("incoming reuqest{}:",request.getRequestURI());

        final String requestHeader = request.getHeader("Authorization");

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
        }

         String token = requestHeader.split("Bearer")[1];

        String username = authUtil.getUsernameFromToken(token);
    }
}
