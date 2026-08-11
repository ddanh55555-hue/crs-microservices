package vn.edu.crs.course_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.crs.course_service.dto.CourseDTO;
import vn.edu.crs.course_service.entity.Course;
import vn.edu.crs.course_service.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {
        // 1. Map từ DTO sang Entity
        Course course = new Course();
        course.setCode(courseDTO.getCode());
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setCredits(courseDTO.getCredits());

        // 2. Lưu vào Database qua Service
        Course savedCourse = courseService.createCourse(course);

        // 3. Map ngược lại DTO để trả về Response
        CourseDTO responseDTO = new CourseDTO();
        responseDTO.setId(savedCourse.getId());
        responseDTO.setCode(savedCourse.getCode());
        responseDTO.setName(savedCourse.getName());
        responseDTO.setDescription(savedCourse.getDescription());
        responseDTO.setCredits(savedCourse.getCredits());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}