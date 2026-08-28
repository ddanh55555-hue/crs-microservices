package vn.edu.crs.registrationservice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.edu.crs.registrationservice.entity.Registration;
import vn.edu.crs.registrationservice.service.RegistrationService;
import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/my")
    public List<Registration> getMyRegistrations(Authentication authentication) {
        Long studentId = (Long) authentication.getCredentials();
        return registrationService.getMyRegistrations(studentId);
    }
}