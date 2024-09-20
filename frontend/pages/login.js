import { useState } from 'react';
import axios from 'axios';
import Link from 'next/link';
import styles from '../styles/login.module.css';
import LoginHeader from '../components/LoginHeader';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const res = await axios.post('/api/login', { email, password });
            console.log(res.data);
        } catch (err) {
            console.error(err);
        }
    };

    return (
        <div className={styles.container}>
            <LoginHeader />
            <h2>Connexion</h2>
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
                    <label htmlFor="password">Mot de passe:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className={styles.input}
                    />
                </div>
                <Link href="/" legacyBehavior>
                    <button className={styles.button}>Connect</button>
                </Link>
            </form>
            <Link href="/signup" legacyBehavior>
                <button className={styles.button}>Create an account</button>
            </Link>
        </div>
    );
};

export default Login;
