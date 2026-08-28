import { useState } from 'react';
import Navbar from '../components/Navbar';
import CourseList from '../components/CourseList';
import type { Course } from '../components/CourseList';
import Pagination from '../components/Pagination';

export default function AdminCoursesPage() {
    const [courses] = useState<Course[]>([
        { id: 1, title: 'Lập trình ReactJS', credits: 3, availableSeats: 37, totalSeats: 40 },
    ]);
    const [page, setPage] = useState<number>(1);
    const [totalPages] = useState<number>(1);

    const handleEdit = (course: Course) => {
        console.log("Edit course:", course);
    };

    const handleDelete = (course: Course) => {
        console.log("Delete course:", course);
    };

    return (
        <div style={{ padding: 24, maxWidth: 800, margin: '0 auto', fontFamily: 'sans-serif' }}>
            <Navbar />
            <h2 style={{ textAlign: 'center', margin: '20px 0' }}>Quản lý khóa học</h2>

            <div style={{ marginTop: 16 }}>
                <CourseList
                    courses={courses}
                    onEdit={handleEdit}
                    onDelete={handleDelete}
                />
            </div>

            <div style={{ marginTop: '20px' }}>
                <Pagination currentPage={page} totalPages={totalPages} onPageChange={setPage} />
            </div>
        </div>
    );
}