import { useState, useEffect } from 'react';
import type { Course, CourseFormValues } from '../types/course';

interface CourseFormProps {
    editingCourse: Course | null;
    onSubmit: (values: CourseFormValues) => Promise<void>;
    onCancel: () => void;
    submitting: boolean;
    serverError: string | null;
}

export default function CourseForm({
                                       editingCourse,
                                       onSubmit,
                                       onCancel,
                                       submitting,
                                       serverError,
                                   }: CourseFormProps) {
    const [tenMonHoc, setTenMonHoc] = useState('');
    const [soTinChi, setSoTinChi] = useState(3);
    const [soChoToiDa, setSoChoToiDa] = useState(50);

    useEffect(() => {
        if (editingCourse) {
            setTenMonHoc(editingCourse.tenMonHoc);
            setSoTinChi(editingCourse.soTinChi);
            setSoChoToiDa(editingCourse.soChoToiDa);
        } else {
            setTenMonHoc('');
            setSoTinChi(3);
            setSoChoToiDa(50);
        }
    }, [editingCourse]);

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        onSubmit({ tenMonHoc, soTinChi, soChoToiDa });
    };

    return (
        <form onSubmit={handleSubmit} style={{ marginBottom: 24, padding: 16, border: '1px solid #ccc' }}>
            <h3>{editingCourse ? 'Cap nhat mon hoc' : 'Them mon hoc moi'}</h3>
            <div style={{ marginBottom: 8 }}>
                <label>Ten mon hoc: </label>
                <input value={tenMonHoc} onChange={(e) => setTenMonHoc(e.target.value)} required />
            </div>
            <div style={{ marginBottom: 8 }}>
                <label>So tin chi: </label>
                <input type="number" value={soTinChi} onChange={(e) => setSoTinChi(Number(e.target.value))} required />
            </div>
            <div style={{ marginBottom: 8 }}>
                <label>So cho toi da: </label>
                <input type="number" value={soChoToiDa} onChange={(e) => setSoChoToiDa(Number(e.target.value))} required />
            </div>
            {serverError && <p style={{ color: 'red' }}>{serverError}</p>}
            <button type="submit" disabled={submitting}>
                {submitting ? 'Dang luu...' : editingCourse ? 'Cap nhat' : 'Them moi'}
            </button>
            {editingCourse && (
                <button type="button" onClick={onCancel} style={{ marginLeft: 8 }}>
                    Huy
                </button>
            )}
        </form>
    );
}