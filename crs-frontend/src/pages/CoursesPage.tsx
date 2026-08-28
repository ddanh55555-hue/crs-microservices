import { useState } from 'react';
import Navbar from '../components/Navbar';
import SearchBox from '../components/SearchBox';
import CourseList from '../components/CourseList';
import Pagination from '../components/Pagination';

export default function CoursesPage() {
    const [courses] = useState([
        { id: 1, title: 'Lập trình ReactJS', credits: 3, availableSeats: 37, totalSeats: 40 },
        { id: 2, title: 'Học xếp hình AI', credits: 3, availableSeats: 36, totalSeats: 36 },
    ]);
    const [page, setPage] = useState(1);
    const [totalPages] = useState(1);

    const handleSearch = (keyword: string) => {
        console.log("Searching:", keyword);
    };

    return (
        <div style={{ padding: 24, maxWidth: 900, margin: '0 auto', fontFamily: 'Arial, sans-serif' }}>
            <Navbar />

            <h2 style={{ textAlign: 'center', margin: '20px 0' }}>Danh sách môn học</h2>

            <div style={{ maxWidth: '500px', margin: '0 auto 20px auto' }}>
                <SearchBox onSearch={handleSearch} />
            </div>

            <div style={{ marginTop: 16 }}>
                <CourseList courses={courses} />
            </div>

            <Pagination currentPage={page} totalPages={totalPages} onPageChange={setPage} />
        </div>
    );
}