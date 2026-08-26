// path: crs-frontend/src/App.tsx
import { useState } from 'react';
import { useCourses } from './api/useCourses';
import { courseApi } from './api/courseApi';
import CourseList from './components/CourseList';
import CourseForm from './components/CourseForm';
import SearchBox from './components/SearchBox';
import type { Course, CourseFormValues } from './types/course';

export default function App() {
    const [keyword, setKeyword] = useState('');
    const [editingCourse, setEditingCourse] = useState<Course | null>(null);
    const { courses, state, errorMessage, refetch } = useCourses(keyword);

    const handleSaveCourse = async (values: CourseFormValues) => {
        if (editingCourse) {
            // Truyền đủ 2 tham số: (id, values)
            await courseApi.updateCourse(editingCourse.id, values);
            setEditingCourse(null);
        } else {
            await courseApi.createCourse(values);
        }
        refetch();
    };

    const handleDeleteCourse = async (id: number) => {
        if (window.confirm('Bạn có chắc chắn muốn xóa môn học này?')) {
            await courseApi.deleteCourse(id);
            refetch();
        }
    };

    return (
        <div style={{ padding: 24, maxWidth: 800, margin: '0 auto' }}>
            <h1>Danh sách môn học</h1>
            <CourseForm
                editingCourse={editingCourse}
                onSubmit={handleSaveCourse}
                onCancel={() => setEditingCourse(null)}
            />

            {/* Sửa prop thành onSearch để khớp với SearchBox.tsx */}
            <SearchBox onSearch={setKeyword} />

            <div style={{ marginTop: 16 }}>
                <CourseList
                    courses={courses}
                    state={state}
                    errorMessage={errorMessage}
                    onRetry={refetch}
                    onEdit={setEditingCourse}
                    onDelete={handleDeleteCourse}
                />
            </div>
        </div>
    );
}