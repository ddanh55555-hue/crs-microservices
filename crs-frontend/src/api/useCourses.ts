// path: crs-frontend/src/api/useCourses.ts
import { useState, useEffect, useCallback } from 'react';
import { courseApi } from './courseApi';
import type { Course } from '../types/course';

export type LoadState = 'loading' | 'success' | 'empty' | 'error';

export function useCourses(keyword: string = '') {
    const [courses, setCourses] = useState<Course[]>([]);
    const [state, setState] = useState<LoadState>('loading');
    const [errorMessage, setErrorMessage] = useState('');

    const fetchCourses = useCallback(async () => {
        setState('loading');
        try {
            const response = await courseApi.getCourses(keyword);

            // XỬ LÝ AN TOÀN: Đảm bảo luôn lấy ra mảng (phòng hờ API trả về phân trang dạng object)
            const rawData = response.data;
            const courseList: Course[] = Array.isArray(rawData)
                ? rawData
                : (rawData as any).content || [];

            setCourses(courseList);
            if (courseList.length === 0) {
                setState('empty');
            } else {
                setState('success');
            }
        } catch (err: unknown) {
            const error = err as { message?: string };
            setErrorMessage(error.message || 'Đã xảy ra lỗi khi tải danh sách.');
            setState('error');
        }
    }, [keyword]);

    useEffect(() => {
        fetchCourses();
    }, [fetchCourses]);

    return { courses, state, errorMessage, refetch: fetchCourses };
}