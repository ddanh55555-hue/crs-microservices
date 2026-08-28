package vn.edu.crs.registrationservice.dto;

public class RegistrationRequest {
    private Long courseId;
    private Long studentId;

    // Constructors
    public RegistrationRequest() {}

    public RegistrationRequest(Long courseId, Long studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    // Getters & Setters
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
}