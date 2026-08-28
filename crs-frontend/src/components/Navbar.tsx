import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function Navbar() {
    const { user, isAuthenticated, logout } = useAuth();

    return (
        <nav style={{ padding: 16, borderBottom: '1px solid #ccc', display: 'flex', gap: 16 }}>
            <Link to="/">Trang chu</Link>

            {isAuthenticated && user?.role === 'STUDENT' && (
                <>
                    <Link to="/register-course">Dang ky hoc phan</Link>
                    <Link to="/my-registrations">Mon hoc da dang ky</Link>
                </>
            )}

            {isAuthenticated && user?.role === 'ADMIN' && (
                <Link to="/courses">Quan ly mon hoc</Link>
            )}

            {isAuthenticated ? (
                <button onClick={logout}>Dang xuat</button>
            ) : (
                <Link to="/login">Dang nhap</Link>
            )}
        </nav>
    );
}