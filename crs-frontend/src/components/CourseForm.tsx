// path: crs-frontend/src/components/CourseForm.tsx
import React, { useState, useEffect } from 'react';
import type { Course, CourseFormValues } from '../types/course';

interface CourseFormProps {
    editingCourse: Course | null;
    onSubmit: (values: CourseFormValues) => Promise<void>;
    onCancel: () => void;
}

export default function CourseForm({ editingCourse, onSubmit, onCancel }: CourseFormProps) {
    const [formData, setFormData] = useState<CourseFormValues>({
        tenMonHoc: '',
        soTinChi: 3,
        soChoToiDa: 40,
    });
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        if (editingCourse) {
            setFormData({
                tenMonHoc: editingCourse.tenMonHoc,
                soTinChi: editingCourse.soTinChi,
                soChoToiDa: editingCourse.soChoToiDa,
            });
        } else {
            setFormData({ tenMonHoc: '', soTinChi: 3, soChoToiDa: 40 });
        }
    }, [editingCourse]);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!formData.tenMonHoc.trim()) return alert('Vui lòng nhập tên môn học!');
        setLoading(true);
        try {
            await onSubmit(formData);
            if (!editingCourse) {
                setFormData({ tenMonHoc: '', soTinChi: 3, soChoToiDa: 40 });
            }
        } finally {
            setLoading(false);
        }
    };

    return (
        <form onSubmit={handleSubmit} style={{ marginBottom: 20, padding: 16, border: '1px solid #ccc', borderRadius: 8 }}>
            <h3>{editingCourse ? 'Cập nhật môn học' : 'Thêm môn học mới'}</h3>
            <div style={{ marginBottom: 8 }}>
                <label>Tên môn học: </label>
                <input
                    type="text"
                    value={formData.tenMonHoc}
                    onChange={(e) => setFormData({ ...formData, tenMonHoc: e.target.value })}
                    style={{ width: '100%', padding: 6, marginTop: 4 }}
                />
            </div>
            <div style={{ display: 'flex', gap: 16, marginBottom: 8 }}>
                <div>
                    <label>Số tín chỉ: </label>
                    <input
                        type="number"
                        value={formData.soTinChi}
                        onChange={(e) => setFormData({ ...formData, soTinChi: Number(e.target.value) })}
                        style={{ padding: 6, marginTop: 4 }}
                    />
                </div>
                <div>
                    <label>Số chỗ tối đa: </label>
                    <input
                        type="number"
                        value={formData.soChoToiDa}
                        onChange={(e) => setFormData({ ...formData, soChoToiDa: Number(e.target.value) })}
                        style={{ padding: 6, marginTop: 4 }}
                    />
                </div>
            </div>
            <div style={{ display: 'flex', gap: 8 }}>
                <button type="submit" disabled={loading} style={{ padding: '6px 12px', background: '#2563eb', color: '#fff', border: 'none' }}>
                    {loading ? 'Đang lưu...' : editingCourse ? 'Cập nhật' : 'Thêm mới'}
                </button>
                {editingCourse && (
                    <button type="button" onClick={onCancel} style={{ padding: '6px 12px' }}>
                        Hủy
                    </button>
                )}
            </div>
        </form>
    );
}