package vn.edu.crs.registration_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.crs.registration_service.dto.RegistrationRequestDTO;
import vn.edu.crs.registration_service.service.RegistrationService;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<?> createRegistration(@RequestBody RegistrationRequestDTO registrationRequestDTO) {
        Object result = registrationService.registerCourse(registrationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}