package vn.edu.crs.courseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.crs.courseservice.entity.Course;
import vn.edu.crs.courseservice.service.CourseService;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasRole('ADMIN')") // Chỉ ADMIN mới có quyền tạo môn học
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(course));
    }

    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PutMapping("/{id}/decrease-slot")
    public ResponseEntity<?> decreaseSlot(@PathVariable Long id) {
        try {
            courseService.decreaseSlot(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(java.util.Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}/increase-slot")
    public ResponseEntity<?> increaseSlot(@PathVariable Long id) {
        courseService.increaseSlot(id);
        return ResponseEntity.ok().build();
    }
}