import axiosClient from './axiosClient';
import type { Course } from '../components/CourseList';

const API_URL = '/courses';

export const getCourses = async () => {
    const response = await axiosClient.get<Course[]>(API_URL);
    return response.data;
};

export const getCourseById = async (id: string | number) => {
    const response = await axiosClient.get<Course>(`${API_URL}/${id}`);
    return response.data;
};

export const createCourse = async (courseData: Omit<Course, 'id'>) => {
    const response = await axiosClient.post<Course>(API_URL, courseData);
    return response.data;
};

export const updateCourse = async (id: string | number, courseData: Partial<Course>) => {
    const response = await axiosClient.put<Course>(`${API_URL}/${id}`, courseData);
    return response.data;
};

export const deleteCourse = async (id: string | number) => {
    const response = await axiosClient.delete(`${API_URL}/${id}`);
    return response.data;
};