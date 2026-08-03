# Blueprint API Specification

## 1. Auth Service (`/api/v1/auth`)
- `POST /api/v1/auth/register` - Đăng ký tài khoản mới
- `POST /api/v1/auth/login` - Đăng nhập và nhận JWT token
- `GET /api/v1/auth/profile` - Lấy thông tin cá nhân (yêu cầu Token)

## 2. Course Service (`/api/v1/courses`)
- `GET /api/v1/courses` - Lấy danh sách khóa học (có phân trang/lọc)
- `GET /api/v1/courses/{id}` - Xem chi tiết một khóa học
- `POST /api/v1/courses` - Tạo mới khóa học (Dành cho Admin)
- `PUT /api/v1/courses/{id}` - Cập nhật khóa học (Dành cho Admin)
- `DELETE /api/v1/courses/{id}` - Xóa khóa học (Dành cho Admin)

## 3. Registration Service (`/api/v1/registrations`)
- `POST /api/v1/registrations` - Sinh viên đăng ký khóa học
- `GET /api/v1/registrations/student/{studentId}` - Xem danh sách môn đã đăng ký của 1 sinh viên
- `DELETE /api/v1/registrations/{id}` - Hủy đăng ký học phần