import { useState } from 'react';
import { useRouter } from 'next/router';
import { useAuth } from '../context/AuthContext'; // Importa el contexto de autenticación
import styles from '../styles/login.module.css';
import LoginHeader from '../components/LoginHeader';

const Signup = () => {
    const { login } = useAuth(); // Usa el método login para autenticar después de registrarse
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState('');
    const router = useRouter();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(''); // Limpia cualquier error previo

        // Validación de contraseña
        if (password !== confirmPassword) {
            setError('Passwords do not match');
            return;
        }

        try {
            const res = await axios.post(`${config.url}auth/register`, { email, password });
            // Si el registro es exitoso, podemos autenticar al usuario automáticamente
            await login(email, password); // Autentica después del registro
            router.push('/'); // Redirige a la página principal
        } catch (err) {
            setError('Registration failed. Please try again.'); // Manejo de errores generales
            console.error(err);
        }
    };

    return (
        <div className={styles.container}>
            <LoginHeader />
            <h2>Create an account</h2>
            {error && <p className={styles.error}>{error}</p>} {/* Muestra errores */}
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
                <div className={styles.formGroup}>
                    <label htmlFor="confirmPassword">Confirm Password:</label>
                    <input
                        type="password"
                        id="confirmPassword"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        className={styles.input}
                    />
                </div>
                <button type="submit" className={styles.button}>
                    Create an account
                </button>
            </form>
            <button className={styles.button} onClick={() => router.push('/login')}>
                Connect
            </button>
        </div>
    );
};

export default Signup;
