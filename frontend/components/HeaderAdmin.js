import styles from '../styles/header.module.css';
import { useAuth } from '../context/AuthContext';

const HeaderAdmin = () => {
  const { logout } = useAuth();


  return (
    <header className={styles.header}>
      <nav className={styles.nav}>
        <button className={styles.button} onClick={logout}>Logout</button>
      </nav>
    </header>
  );
};

export default HeaderAdmin;
