import { useState } from 'react';
import { useRouter } from 'next/router';
import { useAuth } from '../context/AuthContext'; // Importa el contexto de autenticación
import styles from '../styles/login.module.css';
import LoginHeader from '../components/LoginHeader';
import axios from 'axios';
import config from '../config';

const Signup = () => {
    const [username, setUsername] = useState('');
    const [firstname, setFirstname] = useState('');
    const [lastname, setLastname] = useState('');
    const [country, setCountry] = useState('');
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
            await axios.post(`${config.url}auth/register`, { 
                username, 
                firstname, 
                lastname, 
                country, 
                password 
            });
            router.push('/login');
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
                    <label htmlFor="username">Email:</label>
                    <input
                        type="text"
                        id="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        className={styles.input}
                    />
                </div>
                <div className={styles.formGroup}>
                    <label htmlFor="firstname">First Name:</label>
                    <input
                        type="text"
                        id="firstname"
                        value={firstname}
                        onChange={(e) => setFirstname(e.target.value)}
                        className={styles.input}
                    />
                </div>
                <div className={styles.formGroup}>
                    <label htmlFor="lastname">Last Name:</label>
                    <input
                        type="text"
                        id="lastname"
                        value={lastname}
                        onChange={(e) => setLastname(e.target.value)}
                        className={styles.input}
                    />
                </div>
                <div className={styles.formGroup}>
                    <label htmlFor="country">Country:</label>
                    <input
                        type="text"
                        id="country"
                        value={country}
                        onChange={(e) => setCountry(e.target.value)}
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
