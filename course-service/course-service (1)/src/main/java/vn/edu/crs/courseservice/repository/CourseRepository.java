package vn.edu.crs.courseservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.crs.courseservice.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // Đổi tenMonHoc thành name để khớp với entity Course
    Page<Course> findByNameContainingIgnoreCase(String name, Pageable pageable);
}