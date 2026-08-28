import { getCourses } from './courseApi';

export const useCourses = () => {
    // Sử dụng getCourses bình thường sau khi đã được export
    return {
        getCourses,
    };
};