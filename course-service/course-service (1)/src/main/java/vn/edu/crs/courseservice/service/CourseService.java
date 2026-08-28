package vn.edu.crs.courseservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.crs.courseservice.entity.Course;
import vn.edu.crs.courseservice.repository.CourseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public void decreaseSlot(Long id) {
        Course course = getCourseById(id);
        if (course.getAvailableSlots() != null && course.getAvailableSlots() > 0) {
            course.setAvailableSlots(course.getAvailableSlots() - 1);
            courseRepository.save(course);
        } else {
            throw new RuntimeException("No available slots left for course id: " + id);
        }
    }

    public void increaseSlot(Long id) {
        Course course = getCourseById(id);
        int currentSlots = course.getAvailableSlots() != null ? course.getAvailableSlots() : 0;
        course.setAvailableSlots(currentSlots + 1);
        courseRepository.save(course);
    }
}