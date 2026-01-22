package com.AbedProjects.ShopIt.Security;

import com.AbedProjects.ShopIt.User.Authentication.AuthUtil;
import com.AbedProjects.ShopIt.User.UserEntity;
import com.AbedProjects.ShopIt.User.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Configuration
@Slf4j
@EnableWebSecurity
@EnableMethodSecurity
public class JwtSecurityContextPipeLine extends OncePerRequestFilter {

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private HandlerExceptionResolver exceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("incoming request{}:",request.getRequestURI());

      try {
          final String requestHeader = request.getHeader("Authorization");

          if (requestHeader == null || !requestHeader.startsWith("Bearer ")) {
              filterChain.doFilter(request, response);
              return;
          }

          String token = requestHeader.split("Bearer ")[1];

          String username = authUtil.getUsernameFromToken(token);

          if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
              UserEntity user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("username not found"));

              SecurityContextHolder.getContext().
                      setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
          }

          filterChain.doFilter(request, response);
      }
      catch (Exception e) {
          exceptionResolver.resolveException(request, response, null, e);
      }

    }
}
