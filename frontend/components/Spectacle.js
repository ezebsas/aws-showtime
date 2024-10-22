import Link from 'next/link';
import { useCart } from '../context/CartContext';
import styles from '../styles/spectacleList.module.css';

const Spectacle = ({ id, name, date, status, eventSections, venueId }) => {
  const { addToCart } = useCart();

  const handleAddToCart = () => {
    addToCart({ id, name, date, status, eventSections, venueId }); 
  };

  return (
    <div className={styles.spectacle}>
      <h3 className={styles.spectacleTitle}>{name}</h3>
      <p className={styles.spectacleDate}>Date: {date}</p>
      <p className={styles.spectacleStatus}>Status: {status}</p>

      {status !== 'CLOSED' ? (
        <Link href={`/spectacle/${id}`} legacyBehavior>
          <button className={styles.button} onClick={handleAddToCart}>See more</button>
        </Link>
      ): (
          <p>Closed events can&apos;t be booked.</p>
      )}
    </div>
  );
};

export default Spectacle;
