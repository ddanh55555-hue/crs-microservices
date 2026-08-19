package vn.edu.crs.courseservice.repository;

import vn.edu.crs.courseservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
// Thêm 2 dòng import này:
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByTenMonHocIgnoreCase(String tenMonHoc);

    // Thêm hàm tìm kiếm và phân trang này:
    Page<Course> findByTenMonHocContainingIgnoreCase(String keyword, Pageable pageable);
}