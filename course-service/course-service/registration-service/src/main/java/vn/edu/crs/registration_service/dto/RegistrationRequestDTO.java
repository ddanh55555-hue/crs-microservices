package vn.edu.crs.registration_service.dto;

import lombok.Data;

@Data
public class RegistrationRequestDTO {
    private Long studentId;
    private Long courseId;
}