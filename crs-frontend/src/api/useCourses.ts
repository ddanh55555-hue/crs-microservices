import { useState, useEffect, useCallback } from 'react';
import axiosClient from './axiosClient';
import type { Course } from '../types/course';

export type LoadState = 'loading' | 'error' | 'empty' | 'success';

export function useCourses(keyword: string = '', page: number = 0) {
    const [courses, setCourses] = useState<Course[]>([]);
    const [totalPages, setTotalPages] = useState(1);
    const [state, setState] = useState<LoadState>('loading');
    const [errorMessage, setErrorMessage] = useState('');

    const fetchCourses = useCallback(async () => {
        setState('loading');
        try {
            const res = await axiosClient.get('/api/courses', {
                params: { keyword, page, size: 10 },
            });
            const data = res.data.content || res.data;
            setCourses(data);
            setTotalPages(res.data.totalPages || 1);
            setState(data.length === 0 ? 'empty' : 'success');
        } catch (err) {
            setErrorMessage('Khong the tai danh sach mon hoc.');
            setState('error');
        }
    }, [keyword, page]);

    useEffect(() => {
        fetchCourses();
    }, [fetchCourses]);

    return { courses, totalPages, state, errorMessage, refetch: fetchCourses };
}