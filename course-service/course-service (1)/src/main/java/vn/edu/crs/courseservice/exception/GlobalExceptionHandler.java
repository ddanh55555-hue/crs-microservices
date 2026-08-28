package vn.edu.crs.courseservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Bắt ngoại lệ từ chối quyền truy cập từ @PreAuthorize (Spring Security)
    @ExceptionHandler({
            AccessDeniedException.class,
            Exception.class
    })
    public ResponseEntity<?> handleAccessDenied(Exception ex) {
        // Kiểm tra xem có phải do từ chối quyền truy cập không
        if (ex.getClass().getName().contains("AccessDenied") ||
                ex.getClass().getName().contains("AuthorizationDenied")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("status", 403, "error", "Forbidden", "message", "Access Denied"));
        }

        // Với các lỗi hệ thống khác giữ nguyên 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("status", 500, "error", "Internal Server Error", "message", ex.getMessage()));
    }
}