import React from 'react';

export interface Course {
    id: number | string;
    title: string;
    credits?: number;
    availableSeats?: number;
    totalSeats?: number;
}

interface CourseListProps {
    courses: Course[];
    registeredIds?: (number | string)[];
    onRegister?: (course: Course) => void | Promise<void>;
    onUnregister?: (course: Course) => void | Promise<void>;
    registeringId?: number | string | null;
    state?: string;
    errorMessage?: string | null;
    onRetry?: () => void;
    onEdit?: (course: Course) => void;
    onDelete?: (course: Course) => void | Promise<void>;
}

export const CourseList: React.FC<CourseListProps> = ({
                                                          courses,
                                                          registeredIds = [],
                                                          onRegister,
                                                          onUnregister,
                                                          registeringId,
                                                          onEdit,
                                                          onDelete
                                                      }) => {
    const safeCourses = courses || [];

    return (
        <table style={{ width: '100%', borderCollapse: 'collapse', borderTop: '2px solid black', borderBottom: '2px solid black' }}>
            <thead>
            <tr style={{ borderBottom: '1px solid black', textAlign: 'left' }}>
                <th style={{ padding: '10px' }}>Tên môn học</th>
                <th style={{ padding: '10px' }}>Số tín chỉ</th>
                <th style={{ padding: '10px' }}>Số chỗ còn lại</th>
                <th style={{ padding: '10px', textAlign: 'center' }}>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            {safeCourses.map((course) => {
                const total = course.totalSeats ?? 40;
                const available = course.availableSeats ?? 0;

                const isFull = available >= total;
                const isRegistered = registeredIds.includes(course.id);
                const isLoading = registeringId === course.id;

                let btnText = 'Đăng ký';
                let btnDisabled = false;
                const btnStyle: React.CSSProperties = {
                    padding: '4px 15px',
                    backgroundColor: '#f8f9fa',
                    color: '#000',
                    border: '1px solid #ccc',
                    borderRadius: '3px',
                    cursor: 'pointer'
                };

                if (isRegistered) {
                    btnText = 'Hủy đăng ký';
                    btnStyle.backgroundColor = '#f8d7da';
                    btnStyle.color = '#721c24';
                    btnStyle.borderColor = '#f5c6cb';
                } else if (isFull) {
                    btnText = 'Hết chỗ';
                    btnDisabled = true;
                    btnStyle.backgroundColor = '#e9ecef';
                    btnStyle.color = '#6c757d';
                    btnStyle.borderColor = '#ced4da';
                    btnStyle.cursor = 'not-allowed';
                } else if (isLoading) {
                    btnText = 'Đang xử lý...';
                    btnDisabled = true;
                }

                return (
                    <tr key={course.id} style={{ borderBottom: '1px solid #eee' }}>
                        <td style={{ padding: '10px' }}>{course.title}</td>
                        <td style={{ padding: '10px' }}>{course.credits ?? 3}</td>
                        <td style={{ padding: '10px' }}>{available} / {total}</td>
                        <td style={{ padding: '10px', textAlign: 'center' }}>
                            {onEdit || onDelete ? (
                                <div style={{ display: 'flex', gap: '8px', justifyContent: 'center' }}>
                                    {onEdit && (
                                        <button onClick={() => onEdit(course)} style={{ padding: '4px 10px', cursor: 'pointer' }}>Sửa</button>
                                    )}
                                    {onDelete && (
                                        <button onClick={() => onDelete(course)} style={{ padding: '4px 10px', backgroundColor: '#dc3545', color: '#fff', border: 'none', cursor: 'pointer' }}>Xóa</button>
                                    )}
                                </div>
                            ) : (
                                <button
                                    onClick={() => {
                                        if (isRegistered) {
                                            onUnregister && onUnregister(course);
                                        } else {
                                            onRegister && onRegister(course);
                                        }
                                    }}
                                    disabled={btnDisabled}
                                    style={btnStyle}
                                >
                                    {btnText}
                                </button>
                            )}
                        </td>
                    </tr>
                );
            })}
            </tbody>
        </table>
    );
};

export default CourseList;