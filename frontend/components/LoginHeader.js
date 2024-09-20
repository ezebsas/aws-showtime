import Link from 'next/link';
import styles from '../styles/header.module.css';

const LoginHeader = () => {
  return (
    <header className={styles.header}>
      <h1 className={styles.title}>Ticket World</h1>
      <nav className={styles.nav}>
        <Link href="/admin" legacyBehavior>
          <button className={styles.button}>Admin</button>
        </Link>
      </nav>
    </header>
  );
};

export default LoginHeader;
