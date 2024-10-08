import Link from 'next/link';
import { useCart } from '../context/CartContext';
import styles from '../styles/spectacleList.module.css';

const Spectacle = ({ id, name, date, status }) => {
  const { addToCart } = useCart();

  const handleAddToCart = () => {
    addToCart({ id, name, date, status }); 
  };

  return (
    <div className={styles.spectacle}>
      <h3 className={styles.spectacleTitle}>{name}</h3>
      <p className={styles.spectacleDate}>Date: {date}</p>
      <p className={styles.spectacleStatus}>Status: {status}</p>

      <Link href={`/spectacle/${id}`} legacyBehavior>
        <button className={styles.button} onClick={handleAddToCart}>See more</button>
      </Link>
    </div>
  );
};

export default Spectacle;
