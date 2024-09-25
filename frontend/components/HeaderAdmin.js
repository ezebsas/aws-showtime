import Link from 'next/link';
import styles from '../styles/header.module.css';

const HeaderAdmin = ({ setSelectedComponent }) => {
  return (
    <header className={styles.header}>
      <nav className={styles.nav}>
        <Link href="/logout" legacyBehavior>
          <button className={styles.button}>Deconnexion</button>
        </Link>
        <button className={styles.button} onClick={() => setSelectedComponent('statistics')}>Statistics</button>
        <button className={styles.button} onClick={() => setSelectedComponent('events')}>Event management</button>
      </nav>
    </header>
  );
};

export default HeaderAdmin;
