import { useState } from 'react';
import Navbar from '../components/Navbar';
import Toast from '../components/Toast';

interface RegisteredCourse {
    id: number | string;
    title: string;
    credits: number;
}

export default function MyRegistrationsPage() {
    // Dữ liệu mẫu cho các môn đã đăng ký
    const [registeredCourses, setRegisteredCourses] = useState<RegisteredCourse[]>([
        { id: 1, title: 'Lập trình ReactJS', credits: 3 },
    ]);
    const [toast, setToast] = useState<{ message: string; type: 'success' | 'error' } | null>(null);

    const handleCancel = (id: number | string) => {
        setRegisteredCourses(prev => prev.filter(c => c.id !== id));
        setToast({ message: "Hủy đăng ký học phần thành công!", type: "success" });
    };

    return (
        <div style={{ padding: 24, maxWidth: 800, margin: '0 auto', fontFamily: 'Arial, sans-serif' }}>
            <Navbar />
            <h2 style={{ textAlign: 'center', margin: '20px 0' }}>Môn học đã đăng ký</h2>

            {registeredCourses.length === 0 ? (
                <p style={{ textAlign: 'center', color: '#666', marginTop: '20px' }}>Chưa có môn học nào được đăng ký.</p>
            ) : (
                <table style={{ width: '100%', borderCollapse: 'collapse', borderTop: '2px solid black', borderBottom: '2px solid black', marginTop: '20px' }}>
                    <thead>
                    <tr style={{ borderBottom: '1px solid black', textAlign: 'left' }}>
                        <th style={{ padding: '10px' }}>Tên môn học</th>
                        <th style={{ padding: '10px' }}>Số tín chỉ</th>
                        <th style={{ padding: '10px', textAlign: 'center' }}>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    {registeredCourses.map((course) => (
                        <tr key={course.id} style={{ borderBottom: '1px solid #eee' }}>
                            <td style={{ padding: '10px' }}>{course.title}</td>
                            <td style={{ padding: '10px' }}>{course.credits}</td>
                            <td style={{ padding: '10px', textAlign: 'center' }}>
                                <button
                                    onClick={() => handleCancel(course.id)}
                                    style={{
                                        padding: '4px 15px',
                                        backgroundColor: '#dc3545',
                                        color: '#white',
                                        border: 'none',
                                        borderRadius: '3px',
                                        cursor: 'pointer'
                                    }}
                                >
                                    Hủy đăng ký
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}

            {toast && <Toast message={toast.message} type={toast.type} onClose={() => setToast(null)} />}
        </div>
    );
}