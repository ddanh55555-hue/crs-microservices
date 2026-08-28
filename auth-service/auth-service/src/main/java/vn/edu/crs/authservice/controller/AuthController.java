package vn.edu.crs.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // Kiểm tra tài khoản đơn giản
        if ("student1".equals(username) && "student123".equals(password)) {
            return ResponseEntity.ok(Map.of(
                    "token", "mock-jwt-token-student1",
                    "userId", 1,
                    "role", "STUDENT"
            ));
        }

        return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
    }
}