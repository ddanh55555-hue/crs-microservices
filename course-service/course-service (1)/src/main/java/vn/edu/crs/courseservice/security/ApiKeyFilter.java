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
public class ApiKeyFilter extends OncePerRequestFilter {

    private static final String API_KEY_HEADER = "X-API-KEY";
    private static final String VALID_API_KEY = "PARTNER_KEY_123";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        String apiKey = request.getHeader(API_KEY_HEADER);

        // Kiểm tra nếu request gọi vào đường dẫn /public HOẶC có truyền Header X-API-KEY
        if (path.contains("/public") || apiKey != null) {
            if (apiKey == null || !apiKey.equals(VALID_API_KEY)) {
                // Kịch bản 5: Thiếu hoặc Sai API Key -> Chặn ngay lập tức với mã 403
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{\"status\": 403, \"error\": \"Forbidden\", \"message\": \"Invalid or Missing API Key\"}");
                return;
            } else {
                // Kịch bản 6: API Key hợp lệ -> Xác thực thành công cho Partner
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        "partner", null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_PARTNER"))
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}