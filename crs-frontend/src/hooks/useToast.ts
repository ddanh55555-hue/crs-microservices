import { useState } from 'react';

// Phải có chữ 'export' ở đây
export function useToast() {
    const [toast, setToast] = useState<{ message: string; type: 'success' | 'error' } | null>(null);

    const showToast = (message: string, type: 'success' | 'error' = 'success') => {
        setToast({ message, type });
    };

    const clearToast = () => {
        setToast(null);
    };

    return { toast, showToast, clearToast };
}