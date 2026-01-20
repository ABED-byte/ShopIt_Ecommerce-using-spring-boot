package com.AbedProjects.ShopIt.Security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import jakarta.servlet.http.HttpServletResponse;
import thirdProject.config.RoleType;

@Configuration
@Slf4j
public class WebConfig {


     @Autowired
     private JwtAuthFilter jwtAuthFilter;

     @Autowired
     @Qualifier("handlerExceptionResolver")
     private HandlerExceptionResolver handler;

     @Autowired
     private CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
        http.   sessionManagement(sessionconfig -> sessionconfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                authorizeHttpRequests(auth ->auth.
                        requestMatchers("/auth/**", "/", "/index.html", "/login.html", "/signup.html", "/dashboard.html", "/css/**", "/js/**", "/api/public/**").permitAll()
                        .requestMatchers("/patient/**").hasRole(RoleType.PATIENT.name())
                        .requestMatchers("/register/**").hasAnyRole(RoleType.ADMIN.name(),RoleType.DOCTOR.name())
                        .anyRequest().authenticated()).
                csrf(csrf ->csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\":\"Unauthorized\"}");
                        }));

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}


