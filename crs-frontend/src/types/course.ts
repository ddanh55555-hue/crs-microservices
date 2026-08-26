// path: crs-frontend/src/types/course.ts
export interface Course {
    id: number;
    tenMonHoc: string;
    soTinChi: number;
    soChoToiDa: number;
    soChoConLai: number;
}

export interface CourseFormValues {
    tenMonHoc: string;
    soTinChi: number;
    soChoToiDa: number;
}