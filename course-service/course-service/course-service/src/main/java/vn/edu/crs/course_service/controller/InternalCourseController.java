package vn.edu.crs.course_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.crs.course_service.dto.CourseDTO;
import vn.edu.crs.course_service.dto.ErrorResponseDTO;
import vn.edu.crs.course_service.exception.OutOfStockException;
import vn.edu.crs.course_service.service.CourseService;

@RestController
@RequestMapping("/internal/courses")
@RequiredArgsConstructor
public class InternalCourseController {
    private final CourseService courseService;

    @GetMapping
    public Page<CourseDTO> search(@RequestParam(required = false) String keyword, Pageable pageable) {
        return courseService.search(keyword, pageable);
    }

    @PatchMapping("/{id}/reserve-seat")
    public CourseDTO reserveSeat(@PathVariable Long id) {
        return courseService.reserveSeat(id);
    }

    @PatchMapping("/{id}/release-seat")
    public CourseDTO releaseSeat(@PathVariable Long id) {
        return courseService.releaseSeat(id);
    }

    // Bắt lỗi hết chỗ để trả về Status 409 kèm message và soChoConLai = 0
    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<ErrorResponseDTO> handleOutOfStock(OutOfStockException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), ex.getSoChoConLai());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}