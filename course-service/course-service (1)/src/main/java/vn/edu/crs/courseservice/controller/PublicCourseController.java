package vn.edu.crs.courseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.crs.courseservice.service.CourseService;

@RestController
@RequestMapping({"/api/public/courses", "/public/courses"}) // Lắng nghe cả 2 đường dẫn
@RequiredArgsConstructor
public class PublicCourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getPublicCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}