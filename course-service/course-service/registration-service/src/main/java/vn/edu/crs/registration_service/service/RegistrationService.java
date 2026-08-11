package vn.edu.crs.registration_service.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.crs.registration_service.client.CourseClient;
import vn.edu.crs.registration_service.dto.CourseDTO;
import vn.edu.crs.registration_service.dto.RegistrationRequestDTO;
import vn.edu.crs.registration_service.entity.Registration;
import vn.edu.crs.registration_service.repository.RegistrationRepository;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final CourseClient courseClient;

    public Registration registerCourse(RegistrationRequestDTO request) {
        Long studentId = request.getStudentId();
        Long courseId = request.getCourseId();

        // 1. Kiểm tra sinh viên đã đăng ký môn này chưa
        boolean exists = registrationRepository.existsByStudentIdAndCourseIdAndStatus(studentId, courseId, "REGISTERED");
        if (exists) {
            throw new RuntimeException("Sinh viên đã đăng ký môn học này rồi!");
        }

        // 2. Gọi Course Service qua FeignClient để kiểm tra thông tin khóa học
        CourseDTO courseDTO;
        try {
            courseDTO = courseClient.getCourseById(courseId);
        } catch (FeignException e) {
            throw new RuntimeException("Không tìm thấy môn học với ID: " + courseId);
        }

        if (courseDTO == null) {
            throw new RuntimeException("Môn học không tồn tại!");
        }

        // 3. Tạo bản ghi đăng ký khóa học mới
        Registration registration = new Registration();
        registration.setStudentId(studentId);
        registration.setCourseId(courseId);
        registration.setStatus("REGISTERED");

        return registrationRepository.save(registration);
    }
}