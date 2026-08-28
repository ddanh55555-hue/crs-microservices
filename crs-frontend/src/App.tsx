import { Routes, Route, Navigate } from 'react-router-dom';
import RegisterCoursePage from './pages/RegisterCoursePage';
import MyRegistrationsPage from './pages/MyRegistrationsPage';
import ProtectedRoute from './components/ProtectedRoute';
import LoginPage from './pages/LoginPage';
// Import thêm trang Course nếu có (hoặc component tương ứng với /courses)
import CoursesPage from './pages/CoursesPage';

function App() {
    return (
        <Routes>
            <Route path="/" element={<Navigate to="/register-course" replace />} />

            {/* Khai báo Route cho trang đăng nhập */}
            <Route path="/login" element={<LoginPage />} />

            {/* Khai báo Route cho trang danh sách khóa học (/courses) */}
            <Route path="/courses" element={<CoursesPage />} />

            <Route
                path="/register-course"
                element={
                    <ProtectedRoute requiredRole="STUDENT">
                        <RegisterCoursePage />
                    </ProtectedRoute>
                }
            />

            <Route
                path="/my-registrations"
                element={
                    <ProtectedRoute requiredRole="STUDENT">
                        <MyRegistrationsPage />
                    </ProtectedRoute>
                }
            />
        </Routes>
    );
}

export default App;