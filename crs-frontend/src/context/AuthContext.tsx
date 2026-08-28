import { createContext, useState, useContext } from 'react';
import type { ReactNode } from 'react'; // Tách ReactNode thành type import để fix TS1484

export interface AuthUser {
    id: number;
    username: string;
    role: 'ADMIN' | 'STUDENT';
}

// Định nghĩa kiểu dữ liệu rõ ràng thay vì dùng 'any' để fix lỗi ESLint
export interface LoginData {
    userId: number;
    username: string;
    role: 'ADMIN' | 'STUDENT';
}

interface AuthContextType {
    user: AuthUser | null;
    isAuthenticated: boolean;
    login: (data: LoginData) => void;
    logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export function AuthProvider({ children }: { children: ReactNode }) {
    const [user, setUser] = useState<AuthUser | null>(null);

    const login = (data: LoginData) => {
        const authUser: AuthUser = { id: data.userId, username: data.username, role: data.role };
        setUser(authUser);
    };

    const logout = () => {
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, isAuthenticated: !!user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
}

// Thêm dòng này để fix cảnh báo "Fast refresh only works..." của ESLint
// eslint-disable-next-line react-refresh/only-export-components
export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) throw new Error("useAuth must be used within an AuthProvider");
    return context;
};