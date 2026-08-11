package vn.edu.crs.registration_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.crs.registration_service.dto.CourseDTO;

@FeignClient(name = "course-service", url = "http://localhost:8082/courses")
public interface CourseClient {

    @GetMapping("/{id}")
    CourseDTO getCourseById(@PathVariable("id") Long id);
}