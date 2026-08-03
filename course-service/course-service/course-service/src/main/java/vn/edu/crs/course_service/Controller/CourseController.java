package vn.edu.crs.course_service.Controller;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/courses")
public class CourseController {

    // Danh sách lưu tạm dữ liệu trong bộ nhớ (dùng ArrayList để có thể add thêm)
    private final List<Map<String, Object>> courses = new ArrayList<>(List.of(
            new HashMap<>(Map.of(
                    "id", 1,
                    "tenMonHoc", "Lap trinh Java co ban",
                    "soTinChi", 3,
                    "soChoToiDa", 40,
                    "soChoConLai", 12
            )),
            new HashMap<>(Map.of(
                    "id", 2,
                    "tenMonHoc", "Co so du lieu",
                    "soTinChi", 4,
                    "soChoToiDa", 35,
                    "soChoConLai", 0
            ))
    ));

    // 1. Lấy danh sách tất cả môn học (GET http://localhost:8083/courses)
    @GetMapping
    public List<Map<String, Object>> getMockCourses() {
        return courses;
    }

    // 2. Thêm môn học mới (POST http://localhost:8083/courses)
    @PostMapping
    public Map<String, Object> addCourse(@RequestBody Map<String, Object> newCourse) {
        // Tự động sinh ID mới bằng kích thước danh sách + 1
        newCourse.put("id", courses.size() + 1);

        // Thêm môn học mới vào danh sách
        courses.add(newCourse);

        // Trả về dữ liệu môn học vừa tạo
        return newCourse;
    }
}