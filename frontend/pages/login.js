import { useState } from 'react';
import { useAuth } from '../context/AuthContext'; // Importa el contexto de autenticación
import { useRouter } from 'next/router'; // Para redirigir tras login
import styles from '../styles/login.module.css';
import LoginHeader from '../components/LoginHeader';
import Link from 'next/link';

const Login = () => {
    const { login } = useAuth(); // Obtén la función de login desde el contexto
    const router = useRouter();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(''); // Resetea el error antes de intentar el login

        try {
            await login(email, password); // Usa la función de login del contexto
            router.push('/'); // Redirige al usuario tras login exitoso
        } catch (err) {
            setError('Login failed. Please check your credentials.');
        }
    };

    return (
        <div className={styles.container}>
            <LoginHeader />
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
