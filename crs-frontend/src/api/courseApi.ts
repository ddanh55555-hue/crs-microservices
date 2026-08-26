// path: crs-frontend/src/api/courseApi.ts
import axiosClient from './axiosClient';
import type { Course, CourseFormValues } from '../types/course';

export const getCourses = (keyword?: string) => {
    return axiosClient.get<Course[]>('/api/courses', {
        params: { keyword },
    });
};

export const createCourse = (data: CourseFormValues) => {
    return axiosClient.post<Course>('/api/courses', data);
};

export const updateCourse = (id: number, data: CourseFormValues) => {
    return axiosClient.put<Course>(`/api/courses/${id}`, data);
};

export const deleteCourse = (id: number) => {
    return axiosClient.delete(`/api/courses/${id}`);
};