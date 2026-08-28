import { useState } from 'react';
import Navbar from '../components/Navbar';
import SearchBox from '../components/SearchBox';
import CourseList from '../components/CourseList';
import type { Course } from '../components/CourseList';
import Pagination from '../components/Pagination';
import Toast from '../components/Toast';

export default function RegisterCoursePage() {
    const [courses, setCourses] = useState<Course[]>([
        { id: 1, title: 'Lập trình ReactJS', credits: 3, availableSeats: 37, totalSeats: 40 },
        { id: 2, title: 'Học xếp hình AI', credits: 3, availableSeats: 36, totalSeats: 36 },
    ]);

    const [registeredIds, setRegisteredIds] = useState<(number | string)[]>([]);
    const [page, setPage] = useState<number>(1);
    const [totalPages] = useState<number>(1);
    const [registeringId, setRegisteringId] = useState<number | string | null>(null);
    const [toast, setToast] = useState<{ message: string; type: 'success' | 'error' } | null>(null);

    const handleSearch = (keyword: string) => {
        console.log("Searching:", keyword);
    };

    const handleRegister = async (course: Course) => {
        if ((course.availableSeats ?? 0) >= (course.totalSeats ?? 40)) {
            setToast({ message: `Môn học ${course.title} đã hết chỗ!`, type: "error" });
            return;
        }

        if (registeredIds.includes(course.id)) {
            setToast({ message: `Bạn đã đăng ký môn học này rồi!`, type: "error" });
            return;
        }

        setRegisteringId(course.id);
        setTimeout(() => {
            setCourses(prevCourses =>
                prevCourses.map(c =>
                    c.id === course.id ? { ...c, availableSeats: (c.availableSeats ?? 0) + 1 } : c
                )
            );
            setRegisteredIds(prev => [...prev, course.id]);
            setRegisteringId(null);
            setToast({ message: `Đăng ký thành công môn: ${course.title}`, type: "success" });
        }, 500);
    };

    const handleUnregister = async (course: Course) => {
        setRegisteringId(course.id);
        setTimeout(() => {
            setCourses(prevCourses =>
                prevCourses.map(c =>
                    c.id === course.id ? { ...c, availableSeats: Math.max(0, (c.availableSeats ?? 1) - 1) } : c
                )
            );
            setRegisteredIds(prev => prev.filter(id => id !== course.id));
            setRegisteringId(null);
            setToast({ message: `Đã hủy đăng ký môn: ${course.title}`, type: "success" });
        }, 500);
    };

    const clearToast = () => {
        setToast(null);
    };

    return (
        <div style={{ padding: 24, maxWidth: 800, margin: '0 auto', fontFamily: 'Arial, sans-serif' }}>
            <Navbar />
            <h2 style={{ textAlign: 'center', margin: '20px 0' }}>Đăng ký học phần</h2>

            <div style={{ maxWidth: '500px', margin: '0 auto 20px auto' }}>
                <SearchBox onSearch={handleSearch} />
            </div>

            <div style={{ marginTop: 16 }}>
                <CourseList
                    courses={courses}
                    registeredIds={registeredIds}
                    onRegister={handleRegister}
                    onUnregister={handleUnregister}
                    registeringId={registeringId}
                />
            </div>

            <div style={{ marginTop: '20px' }}>
                <Pagination currentPage={page} totalPages={totalPages} onPageChange={setPage} />
            </div>

            {toast && <Toast message={toast.message} type={toast.type} onClose={clearToast} />}
        </div>
    );
}