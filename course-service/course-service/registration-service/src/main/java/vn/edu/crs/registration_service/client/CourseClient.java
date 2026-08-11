package vn.edu.crs.registration_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import vn.edu.crs.registration_service.dto.CourseDTO;

@FeignClient(name = "course-service", url = "http://localhost:8082")
public interface CourseClient {

    @PutMapping("/internal/courses/{id}/reserve-seat")
    CourseDTO reserveSeat(@PathVariable("id") Long id);

    @PutMapping("/internal/courses/{id}/release-seat")
    CourseDTO releaseSeat(@PathVariable("id") Long id);
}