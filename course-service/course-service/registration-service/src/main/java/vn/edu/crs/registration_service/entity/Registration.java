package vn.edu.crs.registration_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "registration")
@Data
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "status")
    private String status; // DA_DANG_KY, DA_HUY
}