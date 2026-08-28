package vn.edu.crs.courseservice.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.edu.crs.courseservice.security.ApiKeyFilter;
import vn.edu.crs.courseservice.security.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final ApiKeyFilter apiKeyFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, ApiKeyFilter apiKeyFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.apiKeyFilter = apiKeyFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(apiKeyFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/internal/**", "/error").permitAll()
                        .requestMatchers("/api/public/**", "/public/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/courses/**", "/courses/**").hasAuthority("ADMIN")
                        .anyRequest().permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"status\": 403, \"error\": \"Forbidden\"}");
                        })
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"status\": 401, \"error\": \"Unauthorized\"}");
                        })
                )
                .build();
    }
}