package vn.edu.crs.courseservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String role = null;

            if ("mock-jwt-token-admin".equals(token)) {
                role = "ADMIN";
            } else if ("mock-jwt-token-student1".equals(token) || "mock-jwt-token-student".equals(token)) {
                role = "STUDENT";
            }

            // Nếu truyền Token nhưng Token không hợp lệ (bị giả mạo) -> Trả về 401 ngay lập tức
            if (role == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"status\": 401, \"error\": \"Unauthorized\", \"message\": \"Invalid Token\"}");
                return; // Dừng filter, không cho request đi tiếp
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    "user", null, Collections.singletonList(new SimpleGrantedAuthority(role))
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}