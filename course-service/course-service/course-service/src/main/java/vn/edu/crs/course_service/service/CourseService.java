package vn.edu.crs.course_service.service;

import vn.edu.crs.course_service.dto.CourseDTO;
import vn.edu.crs.course_service.entity.Course;
import vn.edu.crs.course_service.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khóa học với ID: " + id));
    }

    // --- Bổ sung 3 hàm còn thiếu dưới đây ---

    public Page<CourseDTO> search(String keyword, Pageable pageable) {
        return courseRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    public CourseDTO reserveSeat(Long id) {
        Course course = getCourseById(id);
        // Thêm logic giảm/giữ chỗ nếu cần
        return mapToDTO(courseRepository.save(course));
    }

    public CourseDTO releaseSeat(Long id) {
        Course course = getCourseById(id);
        // Thêm logic tăng/hủy giữ chỗ nếu cần
        return mapToDTO(courseRepository.save(course));
    }

    private CourseDTO mapToDTO(Course course) {
        return CourseDTO.builder()
                .id(course.getId())
                .code(course.getCode())
                .name(course.getName())
                .description(course.getDescription())
                .credits(course.getCredits())
                .build();
    }
}