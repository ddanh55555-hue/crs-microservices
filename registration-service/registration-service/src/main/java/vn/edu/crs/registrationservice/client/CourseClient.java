package vn.edu.crs.registrationservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class CourseClient {

    private final RestTemplate restTemplate;
    private final String courseServiceBaseUrl;

    public CourseClient(RestTemplate restTemplate,
                        @Value("${course-service.base-url:http://localhost:8082}") String courseServiceBaseUrl) {
        this.restTemplate = restTemplate;
        this.courseServiceBaseUrl = courseServiceBaseUrl;
    }

    public void reserveSeat(Long courseId) {
        String url = courseServiceBaseUrl + "/internal/courses/" + courseId + "/reserve-seat";
        try {
            restTemplate.postForEntity(url, null, Void.class);
        } catch (ResourceAccessException e) {
            throw new IllegalStateException("Khong the ket noi toi course-service, vui long thu lai sau");
        }
    }

    public void releaseSeat(Long courseId) {
        String url = courseServiceBaseUrl + "/internal/courses/" + courseId + "/release-seat";
        try {
            restTemplate.postForEntity(url, null, Void.class);
        } catch (ResourceAccessException e) {
            throw new IllegalStateException("Khong the ket noi toi course-service, vui long thu lai sau");
        }
    }
}