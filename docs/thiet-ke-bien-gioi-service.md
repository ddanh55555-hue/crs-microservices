# Tài liệu Thiết kế Biên giới Microservices (Service Boundary Design)

## 1. Nguyên tắc thiết kế & Sở hữu dữ liệu (Data Ownership)
- **Database per Service**: Mỗi Microservice sở hữu hoàn toàn CSDL riêng, các service khác không được trực tiếp truy vấn vào CSDL này.
- **Biên giới nghiệp vụ**: Mỗi service chỉ chịu trách nhiệm đúng 1 domain chuyên biệt.

## 2. Danh sách Microservices

| STT | Tên Service | Chức năng chính | CSDL sở hữu | Giao thức truyền thông |
|---|---|---|---|---|
| 1 | **auth-service** | Quản lý người dùng, phân quyền, đăng nhập/đăng xuất, cấp phát JWT | `auth_db` | REST API |
| 2 | **course-service** | Quản lý thông tin khóa học, môn học, giảng viên, số lượng chỗ | `course_db` | REST API |
| 3 | **registration-service** | Quản lý đăng ký học phần của sinh viên, kiểm tra trùng lịch | `registration_db` | REST / Event-Driven |
| 4 | **api-gateway** | Định tuyến (Routing), Xác thực tập trung, Rate Limiting | *Không có* | HTTP / HTTPS |
| 5 | **crs-frontend** | Giao diện người dùng (React / Angular / Vue) | *Không có* | HTTP / REST |