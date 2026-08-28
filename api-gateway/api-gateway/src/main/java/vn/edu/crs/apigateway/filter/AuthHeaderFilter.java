package vn.edu.crs.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthHeaderFilter implements GlobalFilter, Ordered {

    private static final List<String> OPEN_PATHS = List.of(
            "/api/auth/login"
    );

    // API Key chuẩn dành cho đối tác (Kịch bản 5 & 6)
    private static final String VALID_API_KEY = "SECRET_PARTNER_KEY_123";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // 1. Kiểm tra Route Đối tác (/api/public/**) -> Kịch bản 5 & 6
        if (path.startsWith("/api/public")) {
            String apiKey = request.getHeaders().getFirst("X-API-KEY");
            if (apiKey == null || !apiKey.equals(VALID_API_KEY)) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN); // 403 nếu thiếu hoặc sai API Key
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange);
        }

        // 2. Bỏ qua các API công khai không cần JWT Token
        boolean isOpen = OPEN_PATHS.stream().anyMatch(path::startsWith);
        boolean isPublicCourseRead = path.startsWith("/api/courses") && request.getMethod().name().equals("GET");

        if (isOpen || isPublicCourseRead) {
            return chain.filter(exchange);
        }

        // 3. Kiểm tra JWT Token -> Kịch bản 1
        String authHeader = request.getHeaders().getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ") || authHeader.substring(7).trim().isEmpty()) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED); // 401 nếu thiếu hoặc rỗng Token
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE; // Ưu tiên chạy đầu tiên trước khi routing
    }
}