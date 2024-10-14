import { useState } from 'react';
import { useAuth } from '../context/AuthContext'; // Importa el contexto de autenticación
import { useRouter } from 'next/router'; // Para redirigir tras login
import styles from '../styles/login.module.css';
import Link from 'next/link';
import { jwtDecode } from 'jwt-decode'; // Import jwt-decode

const Login = () => {
    const { login } = useAuth(); // Obtén la función de login desde el contexto
    const router = useRouter();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        try {
            const token = await login(email, password); // Get the token from login
            const decodedToken = jwtDecode(token); // Decode the token
            console.log('Decoded token:', decodedToken); // Log the decoded token
            const userRole = decodedToken.role; // Get the user role
            console.log('User role:', userRole); // Log the user role

            // Redirect based on user role
            if (userRole === 'ADMIN') {
                router.push('/admin'); // Redirect to admin page
            } else if (userRole === 'USER') {
                router.push('/'); // Redirect to user dashboard
            } else {
                setError('Invalid role.'); // Handle unexpected roles
            }
        } catch (err) {
            console.error('Error during login:', err); // Log the error
            setError('Login failed. Please check your credentials.');
        }
    };

    return (
        <div className={styles.container}>
            <h2>Login</h2>
            {error && <p className={styles.error}>{error}</p>}
            <form onSubmit={handleSubmit} className={styles.form}>
                <div className={styles.formGroup}>
                    <label htmlFor="email">Email:</label>
                    <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className={styles.input}
                    />
                </div>
                <div className={styles.formGroup}>
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className={styles.input}
                    />
                </div>
                <button type="submit" className={styles.button}>
                    Connect
                </button>
            </form>
            <Link href="/signup" legacyBehavior>
                <button className={styles.button}>Create an account</button>
            </Link>
        </div>
    );
};

export default Login;
