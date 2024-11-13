import Link from 'next/link';
import styles from '../styles/header.module.css';
import { useAuth } from '../context/AuthContext';

const HeaderAdmin = ({ setSelectedComponent }) => {

  const {logout} = useAuth();

  return (
    <header className={styles.header}>
      <nav className={styles.nav}>
        <Link href="/login" legacyBehavior>
          <button className={styles.button} onClick={logout}>Logout</button>
        </Link>
      </nav>
    </header>
  );
};

export default HeaderAdmin;
