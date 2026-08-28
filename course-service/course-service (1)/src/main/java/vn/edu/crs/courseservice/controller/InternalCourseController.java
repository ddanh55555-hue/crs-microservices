package vn.edu.crs.courseservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/internal/courses")
public class InternalCourseController {

    @PutMapping("/{id}/reserve-seat")
    public ResponseEntity<?> reserveSeat(@PathVariable Long id) {
        // Trả về 200 OK trực tiếp để xác minh route internal mở cho mạng nội bộ
        return ResponseEntity.ok(Map.of(
                "status", "SUCCESS",
                "message", "Reserve seat internal call succeeded for course " + id
        ));
    }
}