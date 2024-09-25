import { useRouter } from 'next/router';
import Link from 'next/link';
import styles from '../styles/header.module.css';

const Header = () => {
  const router = useRouter();

  return (
    <header className={styles.header}>
      <h1 className={styles.title}>Ticket World</h1>
      <nav className={styles.nav}>
        <Link href="/" legacyBehavior>
          <button className={styles.button}>Home</button>
        </Link>
        <Link href="/bookings" legacyBehavior>
          <button className={styles.button}>Bookings</button>
        </Link>
        <Link href="/cart" legacyBehavior>
          <button className={styles.button}>Cart</button>
        </Link>
        <Link href="/login" legacyBehavior>
          <button className={styles.button}>Logout</button>
        </Link>
      </nav>
    </header>
  );
};

export default Header;
