package vn.edu.crs.registration_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "registrations")
@Data
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;
    private Long courseId;
    private String status; // REGISTERED, CANCELLED
}