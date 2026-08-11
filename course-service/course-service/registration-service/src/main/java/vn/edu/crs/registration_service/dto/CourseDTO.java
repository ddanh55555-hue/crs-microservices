package vn.edu.crs.registration_service.dto;

import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String courseCode;
    private String courseName;
    private int availableSeats;
}